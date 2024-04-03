import React, { Component, useState, useEffect } from 'react';
import { Outlet, Link } from "react-router-dom";
import CsvDownloadButton from 'react-json-to-csv'
import { useLocation } from 'react-router-dom';
import { Box, LinearProgress } from '@mui/material';

function UserList() {
	const { state } = useLocation();
	const [userData, setUserData] = useState(null);
	const deptNum = state?.deptNum;
	
	useEffect(() => {	
		fetchData();
	}, []);
	
	const fetchData = async () => {
      try {
        const response = await fetch('api/department/' + deptNum + '/users');
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        const data = await response.json();
        setUserData(data);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };
	
	return userData ? (
		<>
			<div>
				<CsvDownloadButton data={userData} delimiter=',' />
				<center><table className = "List">
					<tr>
						<th><h3>Select Faculty to View</h3></th>
					</tr>
					{userData.map((user, key) => {
						return (
							<tr key={key}>
								<td>
									<nav>
										<ul>
											<Link to="/UserView" state={{ userNum: user.id }}>{user.firstName} {user.lastName}</Link>
										</ul>
									</nav>
								</td>
							</tr>
						)
					})}
				</table></center>
			</div>
			
			<h1>RAW JSON Data</h1>
			<pre>{JSON.stringify(userData, null, 2)}</pre>
		</>
		
	// error handling
	) : deptNum ? (
		<Box sx={{width: "100%"}}>
			<LinearProgress />
			<h1>Loading...</h1>
		</Box>
	) : (
		<p>Error - no department specified</p>
	);
};

export default UserList;
