import { Outlet, Link } from "react-router-dom";

const containerStyle = {
  textAlign: 'center', 
  marginLeft: 'auto', 
  marginRight: 'auto', 
  width: '80%', 
};

const MainLayout = () => {
  return (
    <>
      <nav>
        <ul>
          <li>
            <Link to="/">login</Link>
          </li>
          <li>
            <Link to="/userView">userView</Link>
          </li>
          <li>
            <Link to="/editing">editing</Link>
          </li>
		  <li>
			<Link to="/viewableList">viewableList</Link>
			</li>
			
			
        </ul>
      </nav>
	  
    <div style={containerStyle}>
      <div className="Title">
      <h1>Embry-Riddle Aeronautical University</h1>
      <h2>Faculty Information Repository</h2>
      </div>
    </div>
      <Outlet />
    </>
  )
};

export default MainLayout;