import React, { Component, useState, useEffect } from 'react';
import './App.css';
import ExpandCollapseButton from './ExpandCollapseButton';

import { Box, TextField, Select, MenuItem, InputLabel, Collapse, InputAdornment } from '@mui/material';

//import { Unstable_NumberInput as NumberInput } from '@mui/base/Unstable_NumberInput';

// to do : bold text + make bigger

		
	const	placeholder = [
		{
			value: 'one',
			label: 'Placeholder 1',
		},
		{
			value: 'two',
			label: 'Placeholder 2',
		},
		{
			value: 'three',
			label: 'Placeholder 3',
		},
		{
			value: 'four',
			label: 'Placeholder 4',
		},];

function UserView() {
	const [open1, setOpen1] = React.useState(false);
	const [open2, setOpen2] = React.useState(false);
	const [userData, setUserData] = useState(null);
	const userNum = 12;
	const mainBoxFormat = { fontSize: 36, fontWeight: 'bold', border: 2, borderRadius: 4, borderColor: 'divider', padding:2, margin:2 };
	const subBoxFormat = { 
						
						};

	useEffect(() => {	
		fetchData();
	}, []);
	
	const fetchData = async () => {
      try {
        const response = await fetch('api/user/' + userNum);
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        const data = await response.json();
        setUserData(data);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };
	
	const handleFieldChange = (event) => {
		const { id, value } = event.target;

		// Update field dynamically inside jsonData
		setUserData((prevData) => ({
		...prevData,
		[id]: value,
		}));
	};
	
	const handleINTChange = (event) => {
		const { id, value } = event.target;
		const old = userData[id];

		try {	
			// Update field dynamically inside jsonData
			setUserData((prevData) => ({
			...prevData,
			[id]: parseInt(value) || 0,
			}));
		} catch (error) {
			console.error('Error writing data:', error);
		}
	};
	
	const freeTextField = (id, label) => (
		<TextField
			id={id}
			label={label}
			variant="outlined"
			value={userData?.[id] || ''}
			onChange={handleFieldChange}
		/>
	);
	
	const intField = (id, label, adornment = null) => (
		<TextField
			id={id}
			label={label}
			type="number"
			variant="outlined"
			value={userData?.[id] || ''}
			onChange={handleINTChange}
			InputProps={adornment && {
				startAdornment: <InputAdornment position="start">{adornment}</InputAdornment>,
			}}
		/>
	);
		
	return (
		<>
	
		<div>
			<center>
			<Box sx={mainBoxFormat}>
				General Information
				<ExpandCollapseButton isOpen={open1} onClick={() => setOpen1(!open1)} />
				<Collapse in={open1} timeout="auto" unmountOnExit>
					<Box
						component="form"
						sx={{ p:2, '& .MuiTextField-root': { m: 1, fontSize: 18, width: '30ch' }}}
						noValidate
						autoComplete="off"
					>
					<div>
						{freeTextField("firstName", "First Name")}
						{freeTextField("lastName", "Last Name")}
					</div>
					<div>
						<TextField id="outlined-select-dept" select label="Department" >
							{placeholder.map((option) => (
							<MenuItem key={option.value} value={option.value}>
								{option.label}
							</MenuItem>
							))}
						</TextField>
						<TextField id="outlined-select-rank" select label="Rank">
							{placeholder.map((option) => (
							<MenuItem key={option.value} value={option.value}>
								{option.label}
							</MenuItem>
							))}
						</TextField>
					</div>
					<div>
						<TextField id="outlined-select-load" select label="Load">
							{placeholder.map((option) => (
							<MenuItem key={option.value} value={option.value}>
								{option.label}
							</MenuItem>
							))}
						</TextField>
						<TextField id="outlined-select-NTFSDA" select label="NTFSDA">
							{placeholder.map((option) => (
							<MenuItem key={option.value} value={option.value}>
								{option.label}
							</MenuItem>
							))}
						</TextField>
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
						sx={{ p:2, '& .MuiTextField-root': { m: 1, fontSize: 18, width: '30ch' }}}
						noValidate
						autoComplete="off"
					>
					<div>	
						{intField("journals", "Journals")}
						{intField("books", "Books")}
						{intField("chapters", "Chapters")}
					</div>
					<div>
						{intField("conferences", "Conferences")}
						{intField("patentInnovation", "Patents/Innovations")}
					</div>
					<div>
						{intField("phdAdvised", "PhD Advised")}
						{intField("phdCompleted", "PhD Completed")}
						{intField("msCompleted", "Masters Completed")}
					</div>
					<div>
						{intField("ugMentored", "Undergraduate Student Research Mentored")}
						{intField("researchExperienceStudents", "Research Experience Students")}
						{intField("researchExperienceTotal", "Research Experience Total")}
					</div>
					<div>
						{intField("grants", "Grants", "$K")}
						{intField("awards", "Awards")}
					</div>
					</Box>
				</Collapse>
			</Box>
			</center>
			</div>
			
			<div>
      <h1>RAW JSON Data</h1>
      {userData ? (
        <pre>{JSON.stringify(userData, null, 2)}</pre>
      ) : (
        <p>Loading...</p>
      )}
    </div>
		</>
		);
	}

export default UserView;