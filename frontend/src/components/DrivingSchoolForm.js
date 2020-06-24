import React, { useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import {Button, Dialog, AppBar, Toolbar, Typography, Slide, IconButton, Grid, TextField, FormControl, FormControlLabel, Checkbox, FormLabel, FormGroup} from '@material-ui/core';
import CloseIcon from '@material-ui/icons/Close';
import {serviceConfig} from '../appSettings.js'

const useStyles = makeStyles((theme) => ({
  appBar: {
    position: 'relative',
  },
  title: {
    marginLeft: theme.spacing(2),
    flex: 1,
  },
  grid: { 
    marginTop: theme.spacing(2),
  },
  margin: {
    margin: theme.spacing(1),
  }
}));

const Transition = React.forwardRef(function Transition(props, ref) {
  return <Slide direction="up" ref={ref} {...props} />;
});

export default function FullScreenDialog({showDialog, handleClose, showAlert}) {
  const categories = ['AM', 'A1', 'A2', 'A', 'B1', 'B', 'C1', 'C', 'D1', 'D', 'BE', 'C1E', 'CE', 'D1E', 'DE', 'F', 'M'];
  const classes = useStyles();
  const [state, setState] = useState({
    name: "", 
    shortName: "",
    id: ""
  })
  const [checks, setChecks] = useState({});


  const handleCheck = (event) => {
    const checkbox = event.target;    
    setChecks({...checks, [checkbox.name]:checkbox.checked})
  }

  const handleChangeText = (event) => {
    setState({...state, [event.target.name]:event.target.value});
  }

  const checkboxes = (start, end) => 
   categories.slice(start, end).map((cat) => 
    <FormControlLabel
      key={cat}
      control={<Checkbox name={cat} checked={checks[cat] || false} onChange={handleCheck}/>}
      label={cat}
    />
  )

  const handleSubmit = (event) => {
    event.preventDefault();

    const auth = JSON.parse(localStorage.getItem('auth'));

    let body = (({id, name, shortName}) =>({id, name, shortName}))(state);  
    let catArray = {availableCategories: Object.keys(checks).filter((cat) => checks[cat] === true)};
    body = {...body, ...catArray};

    const requestOptions = {
      method: 'POST',
      headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${auth.token}` 
      },
      body: JSON.stringify(body)
    }

    fetch(`${serviceConfig.baseURL}/drivingSchool`, requestOptions)
    .then(response => {
        if (!response.ok) {
            return Promise.reject(response);
        } 
        showAlert();
        handleClose();
    })
    .catch(response => {
      console.log(response);
    })
  }

  return (
    <Dialog fullScreen open={showDialog} onClose={handleClose} TransitionComponent={Transition}>
        <form onSubmit={handleSubmit}>
        <AppBar className={classes.appBar}>
          <Toolbar>
            <IconButton edge="start" color="inherit" aria-label="close" onClick={handleClose}>
              <CloseIcon/>
            </IconButton>
            <Typography variant="h6" className={classes.title}>
              Driving School
            </Typography>
            <Button autoFocus color="inherit" type="submit">
              save
            </Button>
          </Toolbar>
        </AppBar>
          <Grid 
            container 
            spacing={0}
            direction="column"
            alignItems="center"
            justify="center"
            className={classes.grid}
          >
            <Grid item xs={12}>
            <TextField 
                required
                label="Id" 
                variant="outlined"
                className={classes.margin}
                name="id"
                onChange={handleChangeText}   
            />
            <br/>
            <TextField 
                required
                label="Name" 
                variant="outlined"
                className={classes.margin}
                name="name"
                onChange={handleChangeText}   
              />
              <br/>
              <TextField
                required   
                label="Short name" 
                variant="outlined"
                className={classes.margin}     
                name="shortName"
                onChange={handleChangeText}     
              />
              <br/>
              <FormControl component="fieldset" className={classes.margin}>
                <FormLabel component="legend">Choose driving categories</FormLabel>
                <div style={{display:"flex", justifyContent:"space-between"}}>
                  <FormGroup>
                    {checkboxes(0, categories.length/2)}
                  </FormGroup>
                  <FormGroup>
                    {checkboxes(categories.length/2, categories.length)}
                  </FormGroup>
                </div>
              </FormControl>
            </Grid>
        </Grid>
        </form>
    </Dialog>
  );
}
