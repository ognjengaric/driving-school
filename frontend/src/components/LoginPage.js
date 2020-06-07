import React from 'react';
import {Grid, TextField, Button} from '@material-ui/core';
import {makeStyles} from '@material-ui/styles';

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
                <form>
                    <TextField 
                        size="medium" 
                        label="User id" 
                        variant="outlined"
                        className={classes.margin}                     
                    />
                    <br/>
                    <TextField 
                        size="medium"
                        label="Password" 
                        variant="outlined"
                        className={classes.margin}              
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