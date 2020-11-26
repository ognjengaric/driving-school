import React from 'react';
import { Switch, Route, withRouter } from 'react-router';
import LoginPage from './components/LoginPage';
import HomePage from './components/HomePage';
import RoutesView from './components/RoutesView';
import Streets from './components/Streets'
import SchedulingCalender from './components/SchedulingCalendar'
import DrivingSchools from './components/DrivingSchools'
import TrafficRoute from './components/TrafficRoutes'
import DrivingClasses from './components/DrivingClasses'
import ClassDetails from './components/ClassDetails'
import CourseProgress from './components/CourseProgress';
import Users from './components/Users';



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
            <Route path="/classes" component={DrivingClasses}/>
            <Route path="/class/:id" component={ClassDetails}/>
            <Route path="/course" component={CourseProgress}/>
            <Route path="/users" component={Users}/>
        </Switch>
    );
}


export default withRouter(Routes);