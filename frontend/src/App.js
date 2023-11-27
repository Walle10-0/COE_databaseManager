//import logo from './logo.svg';
import React from 'react';
import './App.css';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Editing from "./pages/Editing";
import Login from "./pages/Login";
import NoPage from "./pages/NoPage";
import UserView from "./pages/UserView";
import ViewableList from "./pages/ViewableList";
import Layout from "./pages/Layout";

function Menu() {
	return (
<BrowserRouter>
<Routes>
<Route path="/" element={<Layout />}>
<Route index element={<Login />} />
<Route path="UserView" element={<UserView />} />
<Route path="ViewableList" element={<ViewableList />} />
<Route path="Editing" element={<Editing />} />
<Route path="*" element={<NoPage />} />
</Route>
</Routes>
</BrowserRouter>
);
}

export default Menu;