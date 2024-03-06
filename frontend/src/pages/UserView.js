import React, { Component, useState, useEffect } from 'react';
import './App.css';
import ExpandCollapseButton from './ExpandCollapseButton';
import { useLocation } from 'react-router-dom';
import CsvDownloadButton from 'react-json-to-csv'

import { Box, TextField, Select, MenuItem, InputLabel, Collapse, InputAdornment,
Table, Paper, TableBody, TableHead, TableRow, TableCell, TableContainer, Button, IconButton, LinearProgress } from '@mui/material';
import SendIcon from '@mui/icons-material/Send';
import AddIcon from '@mui/icons-material/Add';
import DeleteIcon from '@mui/icons-material/Delete';

//import { Unstable_NumberInput as NumberInput } from '@mui/base/Unstable_NumberInput';

// to do : bold text + make bigger

function UserView() {
	const { state } = useLocation();
	const [open1, setOpen1] = React.useState(false);
	const [open2, setOpen2] = React.useState(false);
	const [open3, setOpen3] = React.useState(false);
	const [open4, setOpen4] = React.useState(false);
	const [userData, setUserData] = useState(null);
	const [dropdownData, setDropdownData] = useState(null);
	const dropdownNames = [
		'department',
		'load',
		'rank',
		'status',
		'level',
		];
	const userNum = state?.userNum;
	const mainBoxFormat = { fontSize: 36, fontWeight: 'bold', border: 2, borderRadius: 4, borderColor: 'divider', padding:2, margin:2 };
	

	useEffect(() => {
		async function fetchDataAndSetState() {
			const fetchedUserData = await fetchData('api/user/' + userNum);
			setUserData(fetchedUserData);
			
			const fetchedDropdowns = await Promise.all(
				dropdownNames.map((name) => fetchData('api/allValues/' + name)));
			setDropdownData(fetchedDropdowns);
		}
		fetchDataAndSetState();
	}, []);
	
	// ------- web stuff -------
	
	const fetchData = async (url) => {
      try {
        const response = await fetch(url);
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
		return await response.json();
		
      } catch (error) {
        console.error('Error fetching data:', error);
		return null;
      }
    };
	
	// Send data to the backend via POST
	const postData = async (event) => {
		
		console.log("buttno pressed");
		
		event.preventDefault();
		await fetch('api/user' + (userData.id ? '/' + userData.id : ''), {
        method: (userData.id) ? 'PUT' : 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userData, null, 2),
    });
	}
	
	// ----- logic for writing/fetching data -----
	
	const updateUserData = (value, path) => {
		const updatedUserData = { ...userData };
		try {
			const pathArray = path.split('.');
			let currentField = updatedUserData;

			for (let i = 0; i < pathArray.length - 1; i++) {
				if(currentField[pathArray[i]] == undefined)
				{
					currentField[pathArray[i]] = {}; // add feild if it does not exist
				}
				currentField = currentField?.[pathArray[i]] || {};
			}
			
			currentField[pathArray[pathArray.length - 1]] = value;
			setUserData(updatedUserData);
		} catch (error) {
			console.error('Error writing data:', error);
		}
	};
	
	const getUserField = (path) => {
		try {
			const pathArray = path.split('.');
			let currentField = userData;

			for (let i = 0; i < pathArray.length - 1; i++) {
				currentField = currentField?.[pathArray[i]] || {};
			}
			
			return currentField?.[pathArray[pathArray.length - 1]] || '';
		} catch (error) {
			console.error('Error reading data:', error);
			return '';
		}
	};
	
	// ------ logic for various input feilds -----
	
	const handleMoneyChange = (path, value) => {
		try {
			updateUserData((parseInt(value * 1000) || 0), path);
		} catch (error) {
			console.error('Error writing data:', error);
		}
	};
	
	const handleINTChange = (path, value, operation) => {
		try {
			updateUserData((operation(parseInt(value)) || 0), path)
		} catch (error) {
			console.error('Error writing data:', error);
		}
	};
	
	// ----- feilds in GUI -----
	
	const dropdownField = (id, label) => (
		<TextField
			id={id}
			select
			label={label}
			variant="standard" //"outlined"
			fullWidth
			onChange={(event) => updateUserData(event.target.value, id)}
			value={getUserField(id)}
		>
		{(dropdownData && dropdownData?.length > 0) ? (dropdownData[dropdownNames.indexOf(id.split('.').slice(-1)[0])]?.map((option) => (
			<MenuItem key={option} value={option}>
				{option}
			</MenuItem>
		))) : null}
		</TextField>
	);
	
	const freeTextField = (id, label) => (
		<TextField 
			id={id}
			label={label}
			variant="standard" //"outlined"
			fullWidth
			value={getUserField(id)}
			onChange={(event) => updateUserData(event.target.value, id)}
		/>
	);
	
	const intField = (id, label, adornment = null) => (
		<TextField
			id={id}
			label={label}
			type="number"
			variant="standard" //"outlined"
			fullWidth
			value={getUserField(id)}
			onChange={(event) => handleINTChange(id, event.target.value, null)}
			InputProps={adornment && {
				startAdornment: <InputAdornment position="start">{adornment}</InputAdornment>,
			}}
		/>
	);
		
	const posIntField = (id, label, adornment = null) => (
		<TextField
			id={id}
			label={label}
			type="number"
			variant="standard" //"outlined"
			fullWidth
			value={getUserField(id)}
			onChange={(event) => handleINTChange(id, event.target.value, Math.abs)}
			InputProps={adornment && {
				startAdornment: <InputAdornment position="start">{adornment}</InputAdornment>,
			}}
		/>
	);
	
	const moneyField = (id, label, adornment = null) => (
		<TextField
			id={id}
			label={label}
			type="number"
			variant="standard" //"outlined"
			fullWidth
			value={getUserField(id) / 1000 || ''}
			onChange={(event) => handleMoneyChange(id, event.target.value)}
			InputProps={adornment && {
				startAdornment: <InputAdornment position="start">{adornment}</InputAdornment>,
			}}
		/>
	);
	
	// ---- list operations ----
	const addToListButton = (listFeild) => (
		<IconButton aria-label="add item" color="primary" disabled={!(userData && userData?.[listFeild])} onClick={(event) => {
			const theList = getUserField(listFeild)
			updateUserData([ ...theList, {}], listFeild);
		}}>
			<AddIcon />
		</IconButton>
	);
	
	const deleteFromListButton = (listFeild, index) => (
		<IconButton aria-label="add item" color="primary" onClick={(event) => {
			let theList = getUserField(listFeild)
			theList.splice(index, 1);
			updateUserData(theList, listFeild);
		}}>
			<DeleteIcon />
		</IconButton>
	);
	
	// ----- webpage output -----
	
	return userData ? (
		<>
		<div>
			<CsvDownloadButton data={[userData]} delimiter=',' />
			<center>
			<h1>{userData?.firstName} {userData?.lastName}</h1>
			<Box sx={mainBoxFormat}>
				Account Information
				<ExpandCollapseButton isOpen={open1} onClick={() => setOpen1(!open1)} />
				<Collapse in={open1} timeout="auto" unmountOnExit>
					<Box
						component="form"
						sx={{ width: 1, p:2, '& .MuiTextField-root': { m: 1, fontSize: 18, width: '30ch' }}}
						noValidate
						autoComplete="off"
					>
					<div>
						{freeTextField("firstName", "First Name")}
						{freeTextField("lastName", "Last Name")}
					</div>
					<div>
						{dropdownField("department", "Department")}
						{dropdownField("rank", "Rank")}
						<TextField id="standard-select-role" disabled label="User Role" variant="standard" value={userData?.roleName || ''} />
						{dropdownField("load", "Load")}
						{dropdownField("status", "Status")}
					</div>
					</Box>
				</Collapse>
			</Box>
		
			<Box sx={mainBoxFormat}>
				Scholarly/Academic Information
				<ExpandCollapseButton isOpen={open2} onClick={() => setOpen2(!open2)} />
				<Collapse in={open2} timeout="auto" unmountOnExit>
					<Box
						component="form"
						sx={{ p:2, '& .MuiTextField-root': { m: 1, fontSize: 18, width: '25ch' }}}
						noValidate
						autoComplete="off"
					>
					<div>	
						{posIntField("journals", "Journals")}
						{posIntField("books", "Books")}
						{posIntField("chapters", "Chapters")}
						{posIntField("conferences", "Conferences")}
						{posIntField("patentInnovation", "Patents/Innovations")}
						{posIntField("phdAdvised", "PhD Advised")}
						{posIntField("phdCompleted", "PhD Completed")}
						{posIntField("msCompleted", "Masters Completed")}
						{posIntField("ugMentored", "Undergraduate Student Research Mentored")}
						{posIntField("researchExperienceStudents", "Research Experience Students")}
						{posIntField("researchExperienceTotal", "Research Experience Total")}
						{moneyField("grants", "Grants", "$K")}
						{posIntField("awards", "Awards")}
					</div>
					</Box>
				</Collapse>
			</Box>
			
			<Box sx={mainBoxFormat}>
				Teaching Records
				<ExpandCollapseButton isOpen={open3} onClick={() => setOpen3(!open3)} />
				<Collapse in={open3} timeout="auto" unmountOnExit>
				
			<h5>Classes Taught</h5>
				<TableContainer component={Paper}>
					<Table sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
						<TableHead>
							<TableRow>
								<TableCell>Class Name:</TableCell>
								<TableCell align="right">Semester</TableCell>
								<TableCell align="right">Class Type</TableCell>
								<TableCell align="right">Credits</TableCell>
								<TableCell align="right">Students</TableCell>
							</TableRow>
						</TableHead>
						<TableBody>
						
						{/*Start of table contents - Reading JSON*/}
						{userData && userData?.classes ? (userData.classes.map((row, i) => (
							<TableRow
							key={i}
							>
								<TableCell component="th" scope="row">
									{freeTextField("classes." + i + ".catalog.className", "Class Name")}
								</TableCell>
								<TableCell align="right">{freeTextField("classes." + i + ".semester.fullName", "Semester")}</TableCell>
								<TableCell align="right">{row?.catalog?.classType?.classType}</TableCell>
								<TableCell align="right">{row?.catalog?.creditHours}</TableCell>
								<TableCell align="right">{posIntField("classes." + i + ".students", "Students")}</TableCell>
								<TableCell align="center">{deleteFromListButton("classes", i)}</TableCell>
							</TableRow>
					))) : null}	
					</TableBody>
					</Table>
					</TableContainer>
					{addToListButton("classes")}
			<h5>Other Stuff</h5>
				<TableContainer component={Paper}>
					<Table sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
					<TableBody>
							{userData && userData?.teaching ? (userData.teaching.map((row, i) => (
							<TableRow>
								<TableCell align="right">{freeTextField("teaching." + i + ".semester.fullName", "Semester")}</TableCell>
								<TableCell align="right">{posIntField("teaching." + i + ".newPreps", "New Preps")}</TableCell>
								<TableCell align="right">{posIntField("teaching." + i + ".newDevs", "New Devs")}</TableCell>
								<TableCell align="right">{posIntField("teaching." + i + ".overloads", "Overloads")}</TableCell>
								<TableCell align="center">{deleteFromListButton("teaching", i)}</TableCell>
							</TableRow>
						))) : null}
					</TableBody>
					</Table>
				</TableContainer>
				{addToListButton("teaching")}
				</Collapse>
			</Box>
			
			<Box sx={mainBoxFormat}>
				Service Activity
				<ExpandCollapseButton isOpen={open4} onClick={() => setOpen4(!open4)} />
					
				<Collapse in={open4} timeout="auto" unmountOnExit>
					<TableContainer component={Paper}>
						<Table sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
						<TableBody>
							{userData && userData?.serviceActivity ? (userData.serviceActivity.map((row, i) => (
							<TableRow>
								<TableCell align="right">{freeTextField("serviceActivity." + i + ".semester.fullName", "Semester")}</TableCell>
								<TableCell align="right">{freeTextField("serviceActivity." + i + ".description", "Description")}</TableCell>
								<TableCell align="right">{dropdownField("serviceActivity." + i + ".level.level", "Level")}</TableCell>
								<TableCell align="center">{deleteFromListButton("serviceActivity", i)}</TableCell>
							</TableRow>
						))) : null}
						</TableBody>
						</Table>
					</TableContainer>
					{addToListButton("serviceActivity")}
				</Collapse>
			</Box>
			<Button variant="contained" startIcon={<SendIcon />} onClick={(event) => postData(event)} disabled={!userData}>
				Submit
			</Button>
			</center>
			</div>
			
			<h1>RAW JSON Data</h1>
			<pre>{JSON.stringify(userData, null, 2)}</pre>
		</>
		
	// error handling
    ) : userNum ? (
		<Box sx={{width: "100%"}}>
			<LinearProgress />
			<h1>Loading...</h1>
		</Box>
    ) : (
		<p>Error - no user specified</p>
	);
}

export default UserView;