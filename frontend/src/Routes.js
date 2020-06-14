import React from 'react';
import { Switch, Route, withRouter } from 'react-router';
import LoginPage from './components/LoginPage';
import HomePage from './components/HomePage';




const Routes = () => {

    return(
        <Switch>
            <Route exact path="/" component={LoginPage}/>
            <Route path="/home">
                <HomePage/>
            </Route>
        </Switch>
    );
}


export default withRouter(Routes);