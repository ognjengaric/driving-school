import React from 'react';
import {AppBar, Grid, IconButton, Tooltip} from '@material-ui/core';
import {ExitToAppOutlined, MenuOutlined, HomeOutlined} from '@material-ui/icons';
import {makeStyles} from '@material-ui/styles';
import {DescriptionOutlined as PdfIcon} from '@material-ui/icons'
import Reports from './Reports';
import {useHistory} from "react-router";

const useStyles = makeStyles((theme) => ({
    icon: {
      color: "white",
      scale: 1.2,
    },
    grid: {
        position: "relative"
    },
    btn: {
        float: "right"
    },
    btn_left: {
        float: "left"
    }
  }));

const Header = ({handleChange}) => {
    const [anchorEl, setAnchorEl] = React.useState(null);

    const classes = useStyles();
    let history = useHistory();
    
    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
      };
    
      const handleClose = () => {
        setAnchorEl(null);
      };

    return(
        <AppBar>
            <Grid>
                <Tooltip title="Menu">
                    <IconButton onClick={(e) => handleChange(e, true)} aria-label="open-drawer" className={classes.btn_left}>       
                        <MenuOutlined className={classes.icon}/>
                    </IconButton>
                </Tooltip>
                <Tooltip title="Home">
                    <IconButton aria-label="home" className={classes.btn_left} onClick={() => history.push("/home")}>       
                        <HomeOutlined className={classes.icon}/>
                    </IconButton>
                </Tooltip>
                <Tooltip title="Log out">
                    <IconButton aria-label="log-out" className={classes.btn}>       
                        <ExitToAppOutlined className={classes.icon}/>
                    </IconButton>
                </Tooltip>
                <Tooltip title="Reports">
                    <IconButton aria-label="reports" onClick={handleClick} className={classes.btn}>
                        <PdfIcon className={classes.icon}/>
                    </IconButton>
                </Tooltip>
            </Grid>
            <Reports anchorEl={anchorEl} handleClose={handleClose}/>
        </AppBar>
    );
}

export default Header;