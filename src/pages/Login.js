import React from 'react';
import { useState } from 'react';
import ReactDOM from 'react-dom/client';

function LoginForm() {
	const [name, setName] = useState("");
	
	return (
		<form>
			<label>Enter Server Access ID:
				<input
					type="text"
					value={name}
					onChange={(e) => setName(e.target.value)}
				/>
			</label>
		</form>
	)
}

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
			<div className="Title">
				<h1>Embry-Riddle Aeronautical University</h1>
				<h2>Faculty Information Repository</h2>
			</div>
	
			<div>
			<center><h1>Server Login:</h1></center>
			</div>
			<div>
				<center><table className = "LoginStatement">
					<tr>
						<th><h1 style={fieldtext}>Enter Server Access ID</h1></th>
					</tr>
				</table></center>
			</div>
			
			<div>
				<center><table style={subtext}>
					<td style={subtext}><h4 style={subtext}>*Trouble? Contact Department Head with issues</h4></td>
				</table></center>
			</div>
			
		</>
	);
};

export default login;