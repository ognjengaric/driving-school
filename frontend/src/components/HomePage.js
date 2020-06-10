import React, {useState, useEffect} from 'react';
import Header from './Header';
import Sidebar from './Sidebar';
import {makeStyles} from '@material-ui/styles';
import {useLocation} from 'react-router'
import {Alert} from '@material-ui/lab';
import {Snackbar} from '@material-ui/core';


const useStyles = makeStyles((theme) => ({
    root: {
      display: 'flex',
    }
}));

const HomePage = () => {
    const classes = useStyles();
    const [open, setOpen] = useState(false);
    const [show, setShow] = useState(false);

    let location = useLocation();

    useEffect(() => {
      console.log(location)
      if(location.state)
        if(location.state.prev === 'login')
          setShow(true)
    }, [location]);

    const alert = () => (
      <Snackbar open={show} autoHideDuration={5000} onClose={() => setShow(false)} anchorOrigin={{vertical:"top", horizontal:"right"}}>
          <Alert onClose={() => setShow(false)} severity="success" variant="filled">
              Successfull login!
          </Alert>
      </Snackbar>
  )

    const handleChange = (event, value) => {        
      setOpen(value);
    }

    return(
        <div className={classes.root}>
            <Header handleChange={handleChange}/>
            <Sidebar open={open} handleChange={handleChange}/>
            {alert()}
        </div>
    );
} 
export default HomePage;