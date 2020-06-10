import React, {useState} from 'react';
import Header from './Header';
import Sidebar from './Sidebar';
import {makeStyles} from '@material-ui/styles';


const useStyles = makeStyles((theme) => ({
    root: {
      display: 'flex',
    }
}));

const HomePage = () => {
    const classes = useStyles();
    const [open, setOpen] = useState(false);

    const handleChange = (event, value) => {        
      setOpen(value);
    }

    return(
        <div className={classes.root}>
            <Header handleChange={handleChange}/>
            <Sidebar open={open} handleChange={handleChange}/>
        </div>
    );
} 
export default HomePage;