import React from 'react';

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
								<td>{val.name}</td>
							</tr>
						)
					})}
				</table></center>
			</div>
		</>
	);
};

export default viewableList;