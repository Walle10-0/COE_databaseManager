//import logo from './logo.svg';
import React from 'react';
import './App.css';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Editing from "./pages/Editing";
import Login from "./pages/Login";
import NoPage from "./pages/NoPage";
import UserView from "./pages/UserView";
import DeptList from "./pages/DeptList";
import MyComponent from "./MyComponent";
import UserList from "./pages/UserList";
import Layout from "./pages/Layout";

function Menu() {
	return (
<BrowserRouter>
<Routes>
<Route path="/" element={<Layout />}>
<Route index element={<Login />} />
<Route path="UserView" element={<UserView />} />
<Route path="UserList" element={<UserList />} />
<Route path="DeptList" element={<DeptList />} />
<Route path="Editing" element={<Editing />} />
<Route path="Test" element={<MyComponent />} />
<Route path="*" element={<NoPage />} />
</Route>
</Routes>
</BrowserRouter>
);
}

export default Menu;