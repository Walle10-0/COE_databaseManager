import React from 'react';
import { Outlet, Link } from "react-router-dom";

const fakedata = [
	{ name: "User" },
	{ name: "Test" },
	{ name: "Blargh" },
	{ name: "Guy" },
]

const viewableList = () => {
	return (
		<>
			<div>
				<center><table className = "List">
					<tr>
						<th><h3>Select Faculty to View</h3></th>
					</tr>
					{fakedata.map((val, key) => {
						return (
							<tr key={key}>
							<td>
							<nav>
							<ul>
							<Link to="/UserView">{val.name}</Link>
							</ul>
							</nav>
							</td>
							</tr>
						)
					})}
				</table></center>
			</div>
		</>
	);
};

export default viewableList;
