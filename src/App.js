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

/*
<>

//				<Route exact path='/' exact element={<Home />} />

	<div className="Title">
	<h1>Embry-Riddle Aeronautical University</h1>
	<h2>Faculty Information Repository</h2>
	</div>
	
	<div>
		<center><table className = "Title1">
			<th><h3>General Information</h3></th>
		</table></center>
		
		<center><table>
			<tr className = "NameRank">
				<th><h3>Name</h3></th>
				<td>Value</td>
				<th><h3>Rank</h3></th>
				<td>Value</td>
			</tr>
			
			<tr className = "DepartmentTenure">
				<th><h3>Department</h3></th>
				<td>Value</td>
				<th><h3>NTFSDA</h3></th>
				<td>Value</td>
			</tr>
		</table></center>
		
		<center><table className = "ClassTable">
			<tr className = "Classes">
				<th><h3>Classes:</h3></th>
			</tr><tr>
				<td>Values!</td>
			</tr>
		</table></center>
		
		<br></br><br></br>
		
		<center><table className = "Title2">
			<th><h3>Scholarly Information</h3></th>
		</table></center>
		
		<center><table>
		<tr className = "JornCon">
			<th><h3>Journal Publications</h3></th>
			<td>Value</td>
			<th><h3>Conference Publications</h3></th>
			<td>Value</td>
		</tr>
		
		<tr className = "BookChapters">
			<th><h3>Book Publications</h3></th>
			<td>Value</td>
			<th><h3>Chapters</h3></th>
			<td>Value</td>
		</tr>
		
		<tr className = "PatentAwards">
			<th><h3>Patents/Innovation Disclosures</h3></th>
			<td>Value</td>
			<th><h3>Awards</h3></th>
			<td>Value</td>
		</tr>
		</table></center>

		<center><table className = "AdvisedStudents">
		<tr>
			<th><h3>Number of MS Completed:</h3></th>
			<td>Value</td>
		</tr>
		
		<tr>
			<th><h3>Number of PhD Advised:</h3></th>
			<td>Value</td>
		</tr>
		
		<tr>
			<th><h3>Number of PhD Completed:</h3></th>
			<td>Value</td>
		</tr>
		
		<tr>
			<th><h3>Student Research Projects Mentored:</h3></th>
			<td>Value</td>
		</tr>
		</table></center>
	</div>
	</> */