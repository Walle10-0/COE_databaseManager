import React, { Component, useState, useEffect } from 'react';
import { Outlet, Link } from "react-router-dom";

function DeptList() {
	const [deptData, setDeptData] = useState(null);
	
	useEffect(() => {	
		fetchData();
	}, []);
	
	const fetchData = async () => {
      try {
        const response = await fetch('api/departments');
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        const data = await response.json();
        setDeptData(data);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };
	
	return (
		<>
			<div>
				<center><table className = "List">
					<tr>
						<th><h3>Select Department to View</h3></th>
					</tr>
					{deptData ? (
						deptData.map((dept, key) => {
							return (
								<tr key={key}>
									<td>
										<nav>
											<ul>
												<Link to="/UserList" state={{ deptNum: dept.id }}>{dept.department}</Link>
											</ul>
										</nav>
									</td>
								</tr>
							)
						})
					) : (
						<tr>Loading...</tr>
					)}
				</table></center>
			</div>
			<h1>RAW JSON Data</h1>
			{deptData ? (
				<pre>{JSON.stringify(deptData, null, 2)}</pre>
				) : (
				<p>Loading...</p>
			)}
		</>
	);
};

export default DeptList;