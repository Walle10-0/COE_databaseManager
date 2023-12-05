import { Outlet } from "react-router-dom";

const containerStyle = {
  textAlign: 'center', 
  marginLeft: 'auto', 
  marginRight: 'auto', 
  width: '80%', 
};

const LoginLayout = () => {
  return (
    <>
    <nav>
        <ul>
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

export default LoginLayout;