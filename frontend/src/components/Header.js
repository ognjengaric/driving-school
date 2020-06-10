import React from 'react';
import {AppBar, Grid, IconButton, Tooltip} from '@material-ui/core';
import {ExitToAppOutlined, MenuOutlined} from '@material-ui/icons';
import {makeStyles} from '@material-ui/styles';

const useStyles = makeStyles((theme) => ({
    icon: {
      color: "white",
      scale: 1.2
    },
  }));

const Header = ({handleChange}) => {

    const classes = useStyles();
    
    return(
        <AppBar>
            <Grid container direction="row-reverse" justify="space-between">
                <Tooltip title="Log out">
                    <IconButton aria-label="log-out">       
                        <ExitToAppOutlined className={classes.icon}/>
                    </IconButton>
                </Tooltip>
                <Tooltip title="Menu">
                    <IconButton onClick={(e) => handleChange(e, true)} aria-label="open-drawer">       
                        <MenuOutlined className={classes.icon}/>
                    </IconButton>
                </Tooltip>
            </Grid>
        </AppBar>
    );
}

export default Header;