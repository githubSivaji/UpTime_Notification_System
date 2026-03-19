import { Link } from "react-router-dom";

export default function AboutPage() {
  return (
    <div className="min-h-screen bg-white">

      {/* Top Section */}
      <div className="max-w-4xl mx-auto px-6 py-20 text-center">

        <h1 className="text-5xl font-bold text-gray-900 leading-tight mb-6">
          Built to keep your services online.
        </h1>

        <p className="text-lg text-gray-600">
          Uptime Monitor is a simple yet powerful platform designed to track,
          analyze, and alert you about your system performance — in real time.
        </p>

      </div>

      {/* Divider */}
      <div className="border-t border-gray-200 max-w-5xl mx-auto"></div>

      {/* Story Section */}
      <div className="max-w-5xl mx-auto px-6 py-16 grid md:grid-cols-2 gap-10">

        <div>
          <h2 className="text-2xl font-semibold mb-4">
            Why we built this
          </h2>

          <p className="text-gray-600 leading-relaxed">
            Downtime costs businesses time, money, and trust. We wanted to create
            a tool that makes monitoring simple, reliable, and accessible for
            developers of all levels.
          </p>
        </div>

        <div>
          <h2 className="text-2xl font-semibold mb-4">
            What we believe
          </h2>

          <p className="text-gray-600 leading-relaxed">
            Monitoring shouldn’t be complicated. You should focus on building
            your product while we handle tracking, alerts, and insights.
          </p>
        </div>

      </div>

      {/* Features (Simple list style) */}
      <div className="max-w-4xl mx-auto px-6 py-16">

        <h2 className="text-2xl font-semibold mb-6 text-center">
          Core Capabilities
        </h2>

        <div className="space-y-4 text-gray-700 text-center">

          <p>✔ Real-time uptime monitoring</p>
          <p>✔ Instant downtime alerts</p>
          <p>✔ Performance analytics</p>
          <p>✔ Incident tracking</p>

        </div>

      </div>

      {/* CTA */}
      <div className="bg-gray-50 py-16 text-center">

        <h2 className="text-2xl font-semibold mb-4">
          Ready to monitor your services?
        </h2>

        <Link
          to="/register"
          className="bg-black text-white px-6 py-2 rounded-md hover:bg-gray-800 transition"
        >
          Get Started
        </Link>

      </div>

    </div>
  );
}