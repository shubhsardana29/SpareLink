import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [user, setUser] = useState(null);

  const navigate = useNavigate();

  const handleLogin = async () => {
    try {
      const response = await fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password }),
      });
  
      if (response.ok) {
        // Authentication successful, handle success logic here
        const responseData = await response.json();
        console.log(responseData);
        const { user } = responseData;
        console.log('User role:', user.role); // Log roleId
  
        setUser(user); // Set the user state upon successful login
        setError('');
  
        // Navigate based on user's roleId
        if (user.role === '1') {
          console.log('Navigating to service_team_homepage');
          navigate('/service_team_homepage');
        } else if (user.role === '2') {
          console.log('Navigating to planning_team_homepage');
          navigate('/planning_team_homepage');
        } else if (user.role === '3') {
          console.log('Navigating to warehouse_team_homepage');
          navigate('/warehouse_team_homepage');
        } else if (user.role === '4') {
          console.log('Navigating to customer_support_team_homepage');
          navigate('/customer_support_team_homepage');
        } else {
          // Handle other cases or show an error message
          console.log('Unknown roleId:', user.role);
        }
      } else {
        // Authentication failed, handle error logic here
        setError('Invalid username or password');
      }
    } catch (error) {
      // Handle network errors or other exceptions here
      setError('An error occurred while logging in');
      console.error('Login error:', error);
    }
  };
  

  return (
    <div>
      <h2>Login</h2>
      <input
        type="text"
        placeholder="Username"
        value={username}
        onChange={(e) => {
          setUsername(e.target.value);
          setError(''); // Clear error on input change
        }}
      />
      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={(e) => {
          setPassword(e.target.value);
          setError(''); // Clear error on input change
        }}
      />
      <button onClick={handleLogin}>Login</button>
      {error && <p>{error}</p>}
      {user && <p>Welcome, {user.username}!</p>}
    </div>
  );
}

export default Login;
