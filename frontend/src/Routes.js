import React from 'react';
import { Switch, Route, withRouter } from 'react-router';
import Header from './components/Header';



const Routes = () => {

    return(
        <Switch>
            <Route path="/">
                <Header/>
            </Route>
        </Switch>
    );
}


export default withRouter(Routes);