import React from 'react';
import './App.css';

import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
import InputLabel from '@mui/material/InputLabel';
//import { Unstable_NumberInput as NumberInput } from '@mui/base/Unstable_NumberInput';

// to do : bold text + make bigger
// to do : use dropdowns or tabs to hide unneccesary data

const placeholder = [
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
  },
];

export default function userView() {
	
	const [department] = '';
	
	return (
	<>
	
	<div>
		<center>
		<Box sx={{ fontSize: 34, fontWeight: 'bold' }}>
			General Information
		</Box>

		<Box
			component="form"
			sx={{ border: 1, borderRadius: 2, borderColor: 'divider', '& .MuiTextField-root': { m: 1, width: '30ch' }, padding:1, margin:2}}
			noValidate
			autoComplete="off"
			>
			<div>
				<TextField id="firstName" label="First Name" variant="outlined"/>
				<TextField id="lastName" label="Last Name" variant="outlined" />
			</div>
			<div>
				<TextField
					id="outlined-select-dept"
					select
					label="Department"
				>
					{placeholder.map((option) => (
					<MenuItem key={option.value} value={option.value}>
						{option.label}
					</MenuItem>
					))}
				</TextField>
				<TextField
					id="outlined-select-rank"
					select
					label="Rank"
				>
					{placeholder.map((option) => (
					<MenuItem key={option.value} value={option.value}>
						{option.label}
					</MenuItem>
					))}
				</TextField>
				<TextField
					id="outlined-select-NTFSDA"
					select
					label="NTFSDA"
				>
					{placeholder.map((option) => (
					<MenuItem key={option.value} value={option.value}>
						{option.label}
					</MenuItem>
					))}
				</TextField>
			</div>
		</Box>
		
		<Box sx={{ color: 'text.primary', fontSize: 34, fontWeight: 'bold' }}>
			Scholarly Information
		</Box>
		
		<Box
			component="form"
			sx={{ border: 1, borderRadius: 2, borderColor: 'divider', '& .MuiTextField-root': { m: 1, width: '30ch' }, padding:1, margin:2}}
			noValidate
			autoComplete="off"
			>
			<div>
				<TextField id="Journals" label="Journals" type="number" variant="outlined" defaultValue='0'/>
				<TextField id="Books" label="Books" type="number" variant="outlined" defaultValue='0'/>
				<TextField id="Chapters" label="Chapters" type="number" variant="outlined" defaultValue='0' />
			</div>
			<div>
				<TextField id="Conferences" label="Conferences" type="number" variant="outlined" defaultValue='0'/>
				<TextField id="Awards" label="Awards" type="number" variant="outlined" defaultValue='0'/>
				<TextField id="PatentsInnovations" label="Patents/Innovations" type="number" variant="outlined" defaultValue='0' />
			</div>
		</Box>
		</center>
		</div>
	</>
	);
};