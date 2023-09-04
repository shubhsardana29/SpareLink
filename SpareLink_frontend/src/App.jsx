import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate, Link } from 'react-router-dom';
import Layout from './components/Layout';
import SignUp from './components/SignUp';
import Login from './components/Login';
import Home from './components/Home';
import CustomerSupportTeam from './pages/CustomerSupportTeam';
import PlanningTeam from './pages/PlanningTeam';
import ServiceTeam from './pages/ServiceTeam';
import WarehouseTeam from './pages/WarehouseTeam';

function App() {
  const [isAuthenticated, setIsAuthenticated] = useState(false); // Replace with your authentication logic

  const handleLogout = () => {
    setIsAuthenticated(false); // Reset authentication state to false or initial value
  };

  return (
    <Router>
      <Layout isAuthenticated={isAuthenticated} handleLogout={handleLogout}>
        <Routes>
          <Route path="/signup" element={<SignUp />} />
          <Route path="/login" element={<Login />} />
          <Route
            path="/"
            element={isAuthenticated ? <Home /> : <Navigate to="/login" />} // Use Navigate
          />
          {/* Add routes for the teams */}
          <Route path="/customer_support_team_homepage" element={<CustomerSupportTeam />} />
          <Route path="/planning_team_homepage" element={<PlanningTeam />} />
          <Route path="/service_team_homepage" element={<ServiceTeam />} />
          <Route path="/warehouse_team_homepage" element={<WarehouseTeam />} />
        </Routes>
      </Layout>
    </Router>
  );
}

export default App;
