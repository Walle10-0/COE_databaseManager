import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Login = () => {
  const navigate = useNavigate();
  const [username, setUsername] = useState(''); // New state for username
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const handleLogin = () => {
    // Check both username and password (for demonstration)
    if (username === 'your_username' && password === 'your_password') {
      navigate('/');
    } else {
      setError('Incorrect username or password. Please try again.');
    }
  };

  const navigateToRegistration = () => {
    navigate('/Registration');
  };

  const containerStyle = {
    textAlign: 'center', 
    marginLeft: 'auto', 
    marginRight: 'auto', 
    width: '50%',
  };

  return (
    <div style={containerStyle}>
      <h1>USER LOGIN</h1>
      <input
        type="text"
        placeholder="Username"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
      />
      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      <button onClick={handleLogin}>Login</button>
      <button onClick={navigateToRegistration}>Register</button>
      {error && <p className="error">{error}</p>}
    </div>
  );
};

export default Login;
