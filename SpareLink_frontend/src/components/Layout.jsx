import React from 'react';
import { Link, useLocation } from 'react-router-dom';

function Layout({ children, isAuthenticated, handleLogout }) {
  const location = useLocation();
  const currentRoute = location.pathname;

  return (
    <div>
      <header>
        <nav>
          <ul>
            {currentRoute === '/signup' ? (
              <li>
                <Link to="/login">Login</Link>
              </li>
            ) : currentRoute === '/login' ? (
              <li>
                <Link to="/signup">Sign Up</Link>
              </li>
            ) : (
              <>
                <li>
                  <Link to="/signup">Sign Up</Link>
                </li>
                <li>
                  <Link to="/login">Login</Link>
                </li>
                {/* Conditionally render the Logout button */}
                {isAuthenticated && (
                  <li>
                    <button onClick={handleLogout}>
                      <i className="fa-sign-out-alt"></i> Logout
                    </button>
                  </li>
                )}
              </>
            )}
          </ul>
        </nav>
      </header>
      <main>{children}</main>
    </div>
  );
}

export default Layout;
