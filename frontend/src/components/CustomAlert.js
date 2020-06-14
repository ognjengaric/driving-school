import React from 'react';
import {Alert} from '@material-ui/lab';
import {Snackbar, Zoom} from '@material-ui/core';

const CustomAlert = ({severity, message, show, handleClose}) => {
    return(
        <Snackbar open={show} autoHideDuration={4000} onClose={handleClose} anchorOrigin={{vertical:"top", horizontal:"right"}} TransitionComponent={Zoom}>
            <Alert onClose={handleClose} severity={severity} variant="filled">
                {message}
            </Alert>
        </Snackbar>
    )
} 
export default CustomAlert;