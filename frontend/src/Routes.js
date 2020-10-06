import React from 'react';
import { Switch, Route, withRouter } from 'react-router';
import LoginPage from './components/LoginPage';
import HomePage from './components/HomePage';
import RoutesView from './components/RoutesView';
import Streets from './components/Streets'
import SchedulingCalender from './components/SchedulingCalendar'
import DrivingSchools from './components/DrivingSchools'
import TrafficRoute from './components/TrafficRoutes'



const Routes = () => {
    return(
        <Switch>
            <Route exact path="/" component={LoginPage}/>
            <Route path="/home" component={HomePage}/>
            <Route path="/routes" component={RoutesView}/>
            <Route path="/streets" component={Streets}/>
            <Route path="/schedule" component={SchedulingCalender}/>
            <Route path="/driving-school" component={DrivingSchools}/>
            <Route path="/new-route" component={TrafficRoute}/>
        </Switch>
    );
}


export default withRouter(Routes);