import { Link } from "react-router-dom";

export default function LandingPage() {
  return (
    <div className="min-h-screen bg-white">


      {/* Hero Section */}
      <div className="grid md:grid-cols-2 gap-10 items-center px-6 py-20 max-w-6xl mx-auto">

        {/* Left */}
        <div>
          <h1 className="text-5xl font-bold text-gray-900 mb-6 leading-tight">
            Never Miss Downtime Again ⚡
          </h1>

          <p className="text-gray-600 mb-6">
            Monitor your websites and APIs in real-time. Get instant alerts
            and stay ahead of outages with powerful insights.
          </p>

          <div className="flex gap-4">
            <Link
              to="/register"
              className="bg-blue-600 text-white px-6 py-2 rounded-md hover:bg-blue-700"
            >
              Start Free
            </Link>

            <Link
              to="/about"
              className="border border-gray-300 px-6 py-2 rounded-md hover:bg-gray-100"
            >
              Learn More
            </Link>
          </div>
        </div>

        {/* Right (Mock Dashboard UI Box) */}
        <div className="bg-gray-100 rounded-lg p-6 shadow-inner">

          <div className="bg-white rounded p-4 shadow mb-3">
            <p className="font-semibold">Website Status</p>
            <p className="text-green-500">● All Systems Operational</p>
          </div>

          <div className="bg-white rounded p-4 shadow mb-3">
            <p className="font-semibold">Response Time</p>
            <p className="text-blue-500">120ms Avg</p>
          </div>

          <div className="bg-white rounded p-4 shadow">
            <p className="font-semibold">Incidents</p>
            <p className="text-red-500">1 Active Issue</p>
          </div>

        </div>

      </div>

      {/* Features Section */}
      <div className="bg-gray-50 py-16">

        <h2 className="text-2xl font-bold text-center mb-10">
          Powerful Monitoring Features
        </h2>

        <div className="grid md:grid-cols-3 gap-6 px-6 max-w-6xl mx-auto">

          <div className="bg-white p-6 rounded-lg shadow hover:shadow-lg transition">
            <h3 className="font-semibold mb-2">⚡ Fast Checks</h3>
            <p className="text-gray-600">
              Monitor your services with high-frequency checks.
            </p>
          </div>

          <div className="bg-white p-6 rounded-lg shadow hover:shadow-lg transition">
            <h3 className="font-semibold mb-2">🔔 Smart Alerts</h3>
            <p className="text-gray-600">
              Receive instant alerts when downtime is detected.
            </p>
          </div>

          <div className="bg-white p-6 rounded-lg shadow hover:shadow-lg transition">
            <h3 className="font-semibold mb-2">📊 Insights</h3>
            <p className="text-gray-600">
              Analyze uptime trends and performance metrics.
            </p>
          </div>

        </div>

      </div>


    </div>
  );
}