import React from 'react';
import { Switch, Route, withRouter } from 'react-router';
import LoginPage from './components/LoginPage';
import HomePage from './components/HomePage';
import TrafficRoutes from './components/TrafficRoutes';
import Streets from './components/Streets'



const Routes = () => {
    return(
        <Switch>
            <Route exact path="/" component={LoginPage}/>
            <Route path="/home" component={HomePage}/>
            <Route path="/routes" component={TrafficRoutes}/>
            <Route path="/streets" component={Streets}/>
        </Switch>
    );
}


export default withRouter(Routes);