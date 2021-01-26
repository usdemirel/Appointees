import logo from './logo.svg';
import { Button, Navbar, Nav, NavItem, NavDropdown, MenuItem } from 'react-bootstrap';
import {
  BrowserRouter,
  Route,
  Switch
} from 'react-router-dom';
import Appointment from './Appointment'
import Alert from 'react-s-alert';

function App() {
  return (
  <BrowserRouter>
          {/* <AppHeader authenticated={this.state.authenticated} onLogout={this.handleLogout} /> */}
          <Switch>
            {/* <Route exact path="/" component={Home}></Route> */}
            <Route exact path="/company/:id/appointment" component={Appointment}/>           
            {/* <Route path="/login"
              render={(props) => <Login authenticated={this.state.authenticated} {...props} />}></Route>
            <Route path="/signup"
              render={(props) => <Signup authenticated={this.state.authenticated} {...props} />}></Route>
            <Route path="/oauth2/redirect" component={OAuth2RedirectHandler}></Route>   */}
            {/* <Route component={NotFound}></Route> */}
          </Switch>
        <Alert stack={{limit: 3}} 
          timeout = {3000}
          position='top-right' effect='slide' offset={65} />
    </BrowserRouter>   
  );
}

export default App;
