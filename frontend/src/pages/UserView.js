import React, { Component, useState, useEffect } from 'react';
import './App.css';

import { Box, TextField, Select, MenuItem, InputLabel, Collapse, IconButton} from '@mui/material';
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import KeyboardArrowUpIcon from '@mui/icons-material/KeyboardArrowUp';

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

	useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch('api/user/' + userNum); // Replace this URL with your API endpoint
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        const data = await response.json();
        setUserData(data);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };
	
	fetchData();
	}, []);
	
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
			[id]: parseInt(value),
			}));
		} catch (error) {
			console.error('Error writing data:', error);
		}
	};
		
	return (
		<>
	
		<div>
			<center>
			<Box sx={{ fontSize: 36, fontWeight: 'bold', border: 2, borderRadius: 4, borderColor: 'divider', padding:2, margin:2 }}>
				General Information
				<IconButton
					aria-label="expand row"
					size="small"
					onClick={() => setOpen1(!open1)}
				>
					{open1 ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
				</IconButton>
				<Collapse in={open1} timeout="auto" unmountOnExit>
					<Box
						component="form"
						sx={{ p:2, '& .MuiTextField-root': { m: 1, fontSize: 18, width: '30ch' }}}
						noValidate
						autoComplete="off"
					>
					<div>
						<TextField id="firstName" label="First Name" variant="outlined"
							value={userData?.firstName || ''}
							onChange={handleFieldChange}
						/>
						<TextField id="lastName" label="Last Name" variant="outlined"
							value={userData?.lastName || ''}
							onChange={handleFieldChange}
						/>
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
		
			<Box sx={{ fontSize: 36, fontWeight: 'bold', border: 2, borderRadius: 4, borderColor: 'divider', padding:2, margin:2 }}>
				Scholarly/Academic Information
				<IconButton
					aria-label="expand row"
					size="small"
					onClick={() => setOpen2(!open2)}
				>
					{open2 ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
				</IconButton>
				<Collapse in={open2} timeout="auto" unmountOnExit>
					<Box
						component="form"
						sx={{ p:2, '& .MuiTextField-root': { m: 1, fontSize: 18, width: '30ch' }}}
						noValidate
						autoComplete="off"
					>
					<div>	
						<TextField id="journals" label="Journals" type="number" variant="outlined"
							value={userData?.journals || ''}
							onChange={handleINTChange}
						/>
						<TextField id="books" label="Books" type="number" variant="outlined"
							value={userData?.books || ''}
							onChange={handleINTChange}
						/>
						<TextField id="chapters" label="Chapters" type="number" variant="outlined"
							value={userData?.chapters || ''}
							onChange={handleINTChange}
						/>
					</div>
					<div>
						<TextField id="conferences" label="Conferences" type="number" variant="outlined"
							value={userData?.conferences || ''}
							onChange={handleINTChange}
						/>
						<TextField id="grants" label="Awards" type="number" variant="outlined"
							value={userData?.grants || ''}
							onChange={handleINTChange}
						/>
						<TextField id="patentInnovation" label="Patents/Innovations" type="number" variant="outlined"
							value={userData?.patentInnovation || ''}
							onChange={handleINTChange}
						/>
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