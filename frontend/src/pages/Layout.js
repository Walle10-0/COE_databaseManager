import { Outlet, Link } from "react-router-dom";

const Layout = () => {
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
			<Link to="/userList">viewableList</Link>
			</li>
			
			
        </ul>
      </nav>
	  
	  <div className="Title">
	  <h1>Embry-Riddle Aeronautical University</h1>
	  <h2>Faculty Information Repository</h2>
	  </div>
	  
      <Outlet />
    </>
  )
};

export default Layout;