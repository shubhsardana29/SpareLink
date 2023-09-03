import React from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom'; // Import Navigate
import Layout from './components/Layout';
import SignUp from './components/SignUp';
import Login from './components/Login';
import Home from './components/Home';

function App() {
  const isAuthenticated = false; // Replace with your authentication logic

  return (
    <Router>
      <Layout>
        <Routes>
          <Route path="/signup" element={<SignUp />} />
          <Route path="/login" element={<Login />} />
          <Route
            path="/"
            element={isAuthenticated ? <Home /> : <Navigate to="/login" />} // Use Navigate
          />
        </Routes>
      </Layout>
    </Router>
  );
}

export default App;
