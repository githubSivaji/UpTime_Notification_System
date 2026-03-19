import axios from "axios";

// Create instance
const API = axios.create({
  baseURL: import.meta.env.VITE_BACKEND_URL,
});

// ==============================
// REQUEST INTERCEPTOR
// ==============================
API.interceptors.request.use(
  (config) => {
    const authData = JSON.parse(localStorage.getItem("AUTH_DATA"));

    if (authData?.accessToken) {
      config.headers.Authorization = `Bearer ${authData.accessToken}`;
    }

    return config;
  },
  (error) => Promise.reject(error)
);

// ==============================
// RESPONSE INTERCEPTOR (REFRESH TOKEN)
// ==============================
API.interceptors.response.use(
  (response) => response,
  async (error) => {
    const originalRequest = error.config;

    // If access token expired
    if (
      error.response?.status === 401 &&
      !originalRequest._retry
    ) {
      originalRequest._retry = true;

      try {
        const authData = JSON.parse(localStorage.getItem("AUTH_DATA"));

        // call refresh API
        const res = await axios.post(
          `${import.meta.env.VITE_BACKEND_URL}/auth/refresh`,
          {
            refreshToken: authData.refreshToken,
          }
        );

        const newAccessToken = res.data.data.accessToken;

        // update localStorage
        authData.accessToken = newAccessToken;
        localStorage.setItem("AUTH_DATA", JSON.stringify(authData));

        // attach new token and retry request
        originalRequest.headers.Authorization = `Bearer ${newAccessToken}`;

        return API(originalRequest);

      } catch (err) {
        // refresh failed → logout
        localStorage.removeItem("AUTH_DATA");
        window.location.href = "/login";
      }
    }

    return Promise.reject(error);
  }
);

export default API;