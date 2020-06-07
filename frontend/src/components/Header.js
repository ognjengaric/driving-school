import React from 'react';
import {AppBar, Grid, IconButton, Tooltip} from '@material-ui/core';
import {ExitToAppOutlined} from '@material-ui/icons';
import {makeStyles} from '@material-ui/styles'

const useStyles = makeStyles(() => ({
    icon: {
      color: "white",
      scale: 1.2
    },
  }));

const Header = () => {

      const classes = useStyles();
    
    return(
        <AppBar>
            <Grid container direction="row-reverse" justify="flex-start">
                <Tooltip title="Log out">
                    <IconButton aria-label="log-out">       
                        <ExitToAppOutlined className={classes.icon}/>
                    </IconButton>
                </Tooltip>
            </Grid>
        </AppBar>
    );
}

export default Header;