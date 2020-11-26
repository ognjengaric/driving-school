import React, { useState, useEffect } from 'react';
import {Button, Dialog, MenuItem, AppBar, Toolbar, Typography, Slide, IconButton, Grid, TextField, Select, InputLabel, FormControl, FormControlLabel, Checkbox, FormLabel, FormGroup} from '@material-ui/core';
import CloseIcon from '@material-ui/icons/Close';
import { makeStyles } from '@material-ui/core/styles';
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
      minWidth: '100%'
    }, 
    formControl: {
        marginTop: theme.spacing(1),
        minWidth: '100%'
    },
  }));

  const Transition = React.forwardRef(function Transition(props, ref) {
    return <Slide direction="up" ref={ref} {...props} />;
  });

const UserForm = ({showDialog, handleClose, role, fetchUsers}) => {
    const classes = useStyles();
    const [state, setState] = useState({
        candidateId: "",
        name: "", 
        surname: "",
        password: ""
    })
    const [currentLicence, setCurrentLicence] = useState('');
    const [instructor, setInstructor] = useState('');
    const [school, setSchool] = useState('');
    const [categories, setCategories] = useState([]);
    const [checks, setChecks] = useState({});
    const [schools, setSchools] = useState([]);

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

    const handleCheck = (event) => {
        const checkbox = event.target;    
        setChecks({...checks, [checkbox.name]:checkbox.checked})
    }

    useEffect(() => {
        fetchCategories();
        fetchSchools();
    }, [])

    const fetchCategories = () => {

        const auth = JSON.parse(localStorage.getItem('auth'));
        const requestOptions = {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${auth.token}` 
            }
        }

        fetch(`${serviceConfig.baseURL}/licence`, requestOptions)
        .then(response => {
            if (!response.ok) {
                return Promise.reject(response);
            } 
            return response.json()
        })
        .then(data => setCategories(data))
    }

    const fetchSchools = () => {

        const auth = JSON.parse(localStorage.getItem('auth'));
        const requestOptions = {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${auth.token}` 
            }
        }

        fetch(`${serviceConfig.baseURL}/driving-school`, requestOptions)
        .then(response => {
            if (!response.ok) {
                return Promise.reject(response);
            } 
            return response.json()
        })
        .then(data => setSchools(data))
    }

    const handleSubmit = (event) => {
            event.preventDefault();

            const auth = JSON.parse(localStorage.getItem('auth'));

            let body = (({candidateId, name, surname, password}) => ({candidateId, name, surname, password}))(state);  
            let catArray = Object.keys(checks).filter((cat) => checks[cat] === true);
            //body = {...body, ...catArray};
            body.role = role;
            body.schoolId = school.id;
            if(instructor !== '')
                body.instructorId = instructor.id;
            if(currentLicence !== '')
                body.currentLicence = currentLicence;
            body.licences = catArray;

        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${auth.token}` 
            },
                body: JSON.stringify(body)
            }

        fetch(`${serviceConfig.baseURL}/user`, requestOptions)
        .then(response => {
            if (!response.ok) {
                return Promise.reject(response);
            } 
            handleClose();
            fetchUsers();
            fetchSchools();
            setSchool('')
            setInstructor('')
            setChecks([]);
        })
    }

    const renderSchoolSelect = () => {
        return schools.map(s => <MenuItem key={s.id} value={s}>{s.name}</MenuItem>)
    }

    const renderInstructorSelect = () => {
        if(school !== '')
            return school.instructors.map(i => <MenuItem key={i.id} value={i}>{i.name + " " + i.surname}</MenuItem>)
    }

    const renderCurrLicenceSelect = () => {
        return categories.map(cat => <MenuItem key={cat} value={cat}>{cat}</MenuItem>)
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
                            {role}
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
                    {role === 'Candidate' &&     
                        <TextField 
                            required
                            label="Candidate id" 
                            variant="outlined"
                            className={classes.margin}
                            name="candidateId"
                            onChange={handleChangeText}   
                        />
                    }
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
                            label="Surname" 
                            variant="outlined"
                            className={classes.margin}
                            name="surname"
                            onChange={handleChangeText}   
                        />
                        <br/>
                        <TextField
                            required   
                            label="Password" 
                            variant="outlined"
                            className={classes.margin}     
                            name="password"
                            onChange={handleChangeText}     
                            type="password"
                        />
                        <br/>
                        
                        <FormControl  className={classes.formControl}>
                            <InputLabel id="school">School</InputLabel>
                            <Select
                                labelId="school"
                                value={school}
                                onChange={e => setSchool(e.target.value)}
                                required
                            >
                                {renderSchoolSelect()}
                            </Select>
                        </FormControl>

                        {role === 'Candidate' && 
                        <FormControl  className={classes.formControl}>
                            <InputLabel id="school">Current licence</InputLabel>
                            <Select
                                labelId="school"
                                value={currentLicence}
                                onChange={e => setCurrentLicence(e.target.value)}
                                required
                            >
                                {renderCurrLicenceSelect()}
                            </Select>
                        </FormControl>
                        }

                        {role === 'Candidate' && 
                        <FormControl  className={classes.formControl}>
                            <InputLabel id="school">Instructor</InputLabel>
                            <Select
                                labelId="school"
                                value={instructor}
                                onChange={e => setInstructor(e.target.value)}
                                required
                                disabled={school ? false : true}
                            >
                                {renderInstructorSelect()}
                            </Select>
                        </FormControl>
                        }      
                        
                        <FormControl component="fieldset" className={classes.formControl}>
                            {role === 'Instructor' && <FormLabel component="legend" style={{marginTop:"1%"}}>Choose licences to train</FormLabel>}
                            {role === 'Candidate' && <FormLabel component="legend" style={{marginTop:"1%"}}>Choose owned licences</FormLabel>}
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
    )
}
export default UserForm;
