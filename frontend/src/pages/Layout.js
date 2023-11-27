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
            <Link to="/editing">editing</Link>
          </li>
		  <li>
			<Link to="/viewableList">viewableList</Link>
			</li>
			
			
        </ul>
      </nav>

      <Outlet />
    </>
  )
};

export default Layout;