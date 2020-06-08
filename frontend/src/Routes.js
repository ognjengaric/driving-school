import React from 'react';
import { Switch, Route, withRouter } from 'react-router';
import Header from './components/Header';
import LoginPage from './components/LoginPage';



const Routes = () => {

    return(
        <Switch>
            <Route exact path="/" component={LoginPage}/>
            <Route path="/home">
                <Header/>
            </Route>
        </Switch>
    );
}


export default withRouter(Routes);