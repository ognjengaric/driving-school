import React, { useState } from 'react';
import {serviceConfig} from '../appSettings.js'
import {Grid, TextField, Button} from '@material-ui/core';
import {makeStyles} from '@material-ui/styles';
import {useHistory} from "react-router";

const useStyles = makeStyles((theme) => ({
    margin: {
        margin: theme.spacing(1),
    },
    icon: {        
        scale: 1.2
    },
}));

const LoginPage = () => {
    const classes = useStyles();
    const [state, setState] = useState({
        userId: "",
        password: ""
    });

    let history = useHistory();

    const fetchData = (event) => {
        event.preventDefault();        
        
        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(state)
        }

        fetch(`${serviceConfig.baseURL}/auth/login`, requestOptions)
        .then(response => {
            if (!response.ok) {
                return Promise.reject(response);
            }
            return response.json();
        })
        .then(data => {
            localStorage.setItem('auth', JSON.stringify(data));
            history.push("/home", {prev: 'login'});
        })
        .catch(response => {
            console.log(response);
        })
    }

    const handleChange = (event) => {
        setState({...state, [event.target.name]:event.target.value});
    }

    return(
        <Grid   
            container
            spacing={0}
            direction="column"
            alignItems="center"
            justify="center"
            style={{ minHeight: '100vh' }}
        >
            <Grid item xs={12}>
                <form onSubmit={fetchData}>
                    <TextField 
                        size="medium" 
                        label="User id" 
                        variant="outlined"
                        className={classes.margin}
                        name="userId"
                        onChange={handleChange}         
                    />
                    <br/>
                    <TextField 
                        size="medium"
                        label="Password" 
                        variant="outlined"
                        className={classes.margin}     
                        name="password"
                        onChange={handleChange}      
                        type="password"   
                    />
                    <br/>
                    <Button
                        variant="contained"
                        className={classes.margin}
                        disableElevation
                        color="primary"
                        type="submit"
                    >
                        Log in
                    </Button>
                </form>
            </Grid>
        </Grid>
    );
}

export default LoginPage;