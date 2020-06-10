import React, {useState, useEffect} from 'react';
import Header from './Header';
import Sidebar from './Sidebar';
import CustomAlert from './CustomAlert';
import {makeStyles} from '@material-ui/styles';
import {useLocation} from 'react-router'


const useStyles = makeStyles((theme) => ({
    root: {
      display: 'flex',
    }
}));

const HomePage = () => {
    const classes = useStyles();
    const [open, setOpen] = useState(false);
    const [show, setShow] = useState(false);

    const message = 'Successful login!';

    let location = useLocation();

    const handleClose = () => setShow(false);

    useEffect(() => {
      console.log(location)
      if(location.state)
        if(location.state.prev === 'login')
          setShow(true)
    }, [location]);

    const handleChange = (event, value) => {        
      setOpen(value);
    }

    return(
        <div className={classes.root}>
            <Header handleChange={handleChange}/>
            <Sidebar open={open} handleChange={handleChange}/>
            <CustomAlert severity={'success'} message={message} show={show} handleClose={handleClose}/>
        </div>
    );
} 
export default HomePage;