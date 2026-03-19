import { useNavigate } from "react-router-dom";
import { FaBell, FaUserCircle } from "react-icons/fa";
import { Link } from "react-router-dom";
export default function Navbar() {

  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("token");
    navigate("/login");
  };

  return (
    <div className="w-full bg-white shadow px-6 py-3 flex justify-between items-center">

      {/* Left Section */}
      <div className="flex items-center gap-4">
        <h1 className="text-lg font-bold text-blue-600">
         <Link to="/">Uptime Monitor</Link>
        </h1>
      </div>

      {/* Right Section */}
      <div className="flex items-center gap-6">

        {/* Notifications */}
        {/* <div className="relative cursor-pointer">
          <FaBell className="text-xl text-gray-600 hover:text-black" />
          <span className="absolute -top-2 -right-2 bg-red-500 text-white text-xs px-1 rounded-full">
            3
          </span>
        </div> */}

        {/* User */}
        {/* <div className="flex items-center gap-2 cursor-pointer group">

          <FaUserCircle className="text-2xl text-gray-600" />

          <div className="hidden group-hover:block absolute right-6 top-14 bg-white shadow-md rounded p-2">
            <button
              onClick={handleLogout}
              className="text-sm text-red-500 hover:text-red-700"
            >
              Logout
            </button>
          </div>

        </div> */}
        
        <div className="space-x-4">
            <Link to="/about" className="text-gray-600 hover:text-black">
              About
            </Link>
          <Link to="/login" className="text-gray-600 hover:text-black">
            Login
          </Link>
          <Link
            to="/register"
            className="bg-blue-600 text-white px-4 py-1 rounded"
          >
            Get Started
          </Link>
        </div>

      </div>

    </div>
  );
}