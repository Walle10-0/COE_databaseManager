import React from 'react';
import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Editing from './pages/Editing';
import UserView from './pages/UserView';
import MyComponent from './MyComponent';
import ViewableList from './pages/ViewableList';
import Login from './login';
import Registration from './pages/Registration';
import MainLayout from './pages/MainLayout';
import LoginLayout from './pages/LoginLayout';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<LoginLayout />}>
          <Route index element={<Login />} />
          <Route path="Registration" element={<Registration />} />
        </Route>
        <Route path="/app" element={<MainLayout />}>
          <Route path="UserView" element={<UserView />} />
          <Route path="ViewableList" element={<ViewableList />} />
          <Route path="Editing" element={<Editing />} />
          <Route path="Test" element={<MyComponent />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
