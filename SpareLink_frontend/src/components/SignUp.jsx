import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function SignUp() {
  const [formData, setFormData] = useState({
    username: '',
    password: '',
    roleID: '1', // Default role is Service Team
  });
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch('http://localhost:8080/api/users', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        const responseData = await response.json();
        const { user } = responseData;

        setFormData({
          ...formData,
          roleID: user.roleid,
        });

        // Set isAuthenticated to true after successful signup
        setIsAuthenticated(true);

        // Navigate based on user's roleId
        switch (user.roleid) {
          case '1':
            navigate('/service_team_homepage');
            break;
          case '2':
            navigate('/planning_team_homepage');
            break;
          case '3':
            navigate('/warehouse_team_homepage');
            break;
          case '4':
            navigate('/customer_support_team_homepage');
            break;
          default:
            console.log('Unknown roleId:', user.roleid);
        }
      } else {
        console.error('Signup failed');
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  return (
    <div>
      <h2>Sign Up</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="username">Username</label>
          <input
            type="text"
            id="username"
            name="username"
            value={formData.username}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label htmlFor="password">Password</label>
          <input
            type="password"
            id="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label htmlFor="roleID">Role</label>
          <select
            id="roleID"
            name="roleID"
            value={formData.roleID}
            onChange={handleChange}
            required
          >
            <option value="1">Service Team</option>
            <option value="2">Planning Team</option>
            <option value="3">Warehouse Team</option>
            <option value="4">Customer Support Team</option>
          </select>
        </div>
        <button type="submit">Sign Up</button>
      </form>
    </div>
  );
}

export default SignUp;
