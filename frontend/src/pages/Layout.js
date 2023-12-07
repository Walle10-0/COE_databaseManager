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
			<Link to="/deptList">viewableList</Link>
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