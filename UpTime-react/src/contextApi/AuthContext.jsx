import { createContext, useContext, useState, useEffect } from "react";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {

  const stored = localStorage.getItem("AUTH_DATA");

  const [auth, setAuth] = useState(
    stored
      ? JSON.parse(stored)
      : {
          accessToken: null,
          refreshToken: null,
        }
  );

  useEffect(() => {
    localStorage.setItem("AUTH_DATA", JSON.stringify(auth));
  }, [auth]);

  // ✅ LOGIN
  const login = (response) => {
    const data = response.data.data;

    setAuth({
      accessToken: data.accessToken,
      refreshToken: data.refreshToken,
    });
  };

  // ✅ LOGOUT
  const logout = () => {
    setAuth({
      accessToken: null,
      refreshToken: null,
    });

    localStorage.removeItem("AUTH_DATA");
  };

  // ✅ UPDATE ACCESS TOKEN (refresh case)
  const updateAccessToken = (newToken) => {
    setAuth((prev) => ({
      ...prev,
      accessToken: newToken,
    }));
  };

  return (
    <AuthContext.Provider
      value={{
        accessToken: auth.accessToken,
        refreshToken: auth.refreshToken,
        login,
        logout,
        updateAccessToken,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);