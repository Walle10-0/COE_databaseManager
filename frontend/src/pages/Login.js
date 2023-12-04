import React from 'react';
import { useState } from 'react';
import ReactDOM from 'react-dom/client';
import TextField from '@mui/material/TextField';

const login = () => {
	const subtext = {
		color: "gold",
		textAlign: "left",
		borderBottom: "white",
		borderRight: "white",
		borderLeft: "white",
		borderTop: "white",
		marginTop: "2px"
	};
		
	const fieldtext = {
		color: "gray",
	};
	
	return (
		<>
			<div>
			<center><h1>Server Login:</h1>
				<h1><TextField
				id="accessID"
				label="Server Access ID"
				variant="outlined"
				/></h1>
				<h1><TextField
				id="accessPass"
				label="Server Access Password"
				type="password"
				variant="outlined"
				helperText="Trouble? Contact Department Head with issues"
				/></h1></center>
			</div>
			
		</>
	);
};

export default login;