import React, { useEffect, useState, useRef } from 'react';
import HomePage from './HomePage';
import {useParams} from 'react-router';
import {serviceConfig} from '../appSettings.js'
import { Grid, Typography, Paper, Select, MenuItem, FormControl, InputLabel, FormGroup, FormControlLabel, Checkbox, Button } from '@material-ui/core';
import {AccessTime, DirectionsCar} from '@material-ui/icons';  
import { makeStyles } from '@material-ui/core/styles';
import {Microsoft, loadBingApi} from '../bingMapService.ts';
import CustomAlert from './CustomAlert';

const useStyles = makeStyles((theme) => ({
    paper: {
      padding: theme.spacing(2),
      textAlign: 'center',
      color: theme.palette.text.primary,
    },
  }));

const ClassDetails = () => {

    const message = 'You have to choose a route or check driving range!';

    let { id } = useParams();
    const classes = useStyles();
    const mapRef = useRef();
    const [routes, setRoutes]  = useState([]);
    const [data, setData] = useState({
        id: '',
        instructor: '', 
        candidate: '',
        startDate: '',
        endDate: ''
    });
    const [map, setMap] = useState('');
    const [show, setShow] = useState(false);
    const [selected, setSelected] = useState(null);
    const [checks, setChecks] = useState({
        range: false, 
        load: false,
      })

    useEffect(() => {
        loadBingApi(serviceConfig.bingApiKey).then(() => {
          initMap();
        });
        // eslint-disable-next-line
      }, []);

    useEffect(() => {
        fetchClassData()
        // eslint-disable-next-line
    }, [])

    useEffect(() => {
        if(data.licenceCategory !== undefined)
            fetchRoutes()
        // eslint-disable-next-line
    }, [data.licenceCategory])

    useEffect(() => {
        if(routes !== [])
            setInfoIfFinished();
        // eslint-disable-next-line
    }, [routes, data, map])

    const fetchClassData = () => {
        const auth = JSON.parse(localStorage.getItem('auth'));
        const requestOptions = {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${auth.token}` 
            }
        }
        fetch(`${serviceConfig.baseURL}/class/${id}`, requestOptions)
        .then(response => {
            if (!response.ok) {
                return Promise.reject(response);
            } 
            return response.json()
        })
        .then(data => {
            setData(data)  
        })
    }

    const fetchRoutes = () => {
        const auth = JSON.parse(localStorage.getItem('auth'));
        const requestOptions = {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${auth.token}` 
            }
        }
        fetch(`${serviceConfig.baseURL}/route/?category=${data.licenceCategory}`, requestOptions)
        .then(response => {
            if (!response.ok) {
                return Promise.reject(response);
            } 
            return response.json()
        })
        .then(data => {
            setRoutes(data)
        })
    }
    
    const reformatDate = (date) => {
        if(date !== undefined)
            return new Date(date).toLocaleString();
    }; 

    const reformatText = (text) => {
        if(text !== undefined)
            return text[0] + text.substring(1, text.length).toLowerCase();
    }; 
    
    const initMap = () => {
        const map = new Microsoft.Maps.Map(mapRef.current);
        setMap(map);
    }

    const renderSelect = () => {
        return routes.map(el =>
            <MenuItem key={el.id} value={el}>{(el.id)}</MenuItem>
        )
    }

    const handleSelect = (e) => {
        setSelected(e.target.value)
        drawRoute(e.target.value)
    }

    const drawRoute = (route) => {
        if(map.entities._primitives.length !== 0){
            map.entities.pop();
        }            

        var locations = Microsoft.Maps.PointCompression.decode(route.encodedCoordinates);

        var p = new Microsoft.Maps.Polyline(locations, {
            strokeColor: 'red',
            strokeThickness: 5
        });
        map.entities.push(p);
        map.setView({ bounds: Microsoft.Maps.LocationRect.fromLocations(locations) });
    }

    const formatDuration = (duration) => {
        if(duration < 60)
          return duration + 's'
        else
          return `${Math.floor(duration/60)}min ${duration%60 ? duration % 60 + 's' : ''}`;
    }

    const setClassStatus = (status) => {
        const payload = {id : data.id, status};

        const auth = JSON.parse(localStorage.getItem('auth'));
        const requestOptions = {
            method: 'PATCH',
            headers: {
                'Authorization': `Bearer ${auth.token}` ,
                'Content-Type': 'application/json'
            }, 
            body: JSON.stringify(payload)
        }
        fetch(`${serviceConfig.baseURL}/class/status`, requestOptions)
        .then(response => {
            if (!response.ok) {
                return Promise.reject(response);
            } 
            fetchClassData();
        })
    }

    const completeClass = (status) => {
        const payload = {id : data.id, status, route: selected, isOnDrivingRange: checks.range, isWithLoad: checks.load};

        const auth = JSON.parse(localStorage.getItem('auth'));
        const requestOptions = {
            method: 'PATCH',
            headers: {
                'Authorization': `Bearer ${auth.token}` ,
                'Content-Type': 'application/json'
            }, 
            body: JSON.stringify(payload)
        }
        fetch(`${serviceConfig.baseURL}/class/complete`, requestOptions)
        .then(response => {
            if (!response.ok) {
                return Promise.reject(response);
            } 
            fetchClassData();
        })
    }

    const handleComplete = () => {
        if(selected === null && !checks.range){
            setShow(true);
            return
        }

        completeClass('FINISHED');
    }

    const handleChange = (event) => {
        setChecks({ ...checks, [event.target.name]: event.target.checked });
    };

    const setInfoIfFinished = () => {
        if(data.status !== 'FINISHED'){
            return;    
        }
        
        setChecks({load : data.isWithLoad, range: data.isOnDrivingRange});
        
        if(data.route !== null){
            let array = routes.filter(el => el.id === data.route.id)
            setSelected(array[0]);
            if(map && array[0])
                drawRoute(array[0]);
        }

    }

    return(
        <div>
            <HomePage/>
            <div style={{margin:"3%"}}>
                <Grid container spacing={3}>
                    <Grid item xs>
                        <Paper className={classes.paper}>
                            <Typography variant="caption" component="h2">Candidate</Typography>
                            <Typography variant="h6" component="h2">{data.candidate}</Typography>
                        </Paper>
                    </Grid>
                    <Grid item xs={2}>
                        <Paper className={classes.paper}>
                        <Typography variant="caption" component="h2">Id</Typography>
                            <Typography variant="h6" component="h2">{data.id}</Typography>
                        </Paper>
                    </Grid>
                    <Grid item xs={2}>
                        <Paper className={classes.paper}>
                        <Typography variant="caption" component="h2">Status</Typography>
                            <Typography variant="h6" component="h2">{reformatText(data.status)}</Typography>
                        </Paper>
                    </Grid>
                    <Grid item xs>
                        <Paper className={classes.paper}>
                            <Typography variant="caption" component="h2">Instructor</Typography>
                            <Typography variant="h6" component="h2">{data.instructor}</Typography>
                        </Paper>
                    </Grid>    
                </Grid>
                <Grid container spacing={3}>
                    <Grid item xs>
                        <Paper className={classes.paper}>
                            <Typography variant="caption" component="h2">Start time</Typography>
                            <Typography variant="h6" component="h2">{reformatDate(data.startDate)}</Typography>
                        </Paper>
                    </Grid>
                    <Grid item xs>
                            <Paper className={classes.paper}>
                                <Typography variant="caption" component="h2">Category</Typography>
                                <Typography variant="h6" component="h2">{data.licenceCategory}</Typography>
                            </Paper>
                    </Grid>
                    <Grid item xs>
                        <Paper className={classes.paper}>
                            <Typography variant="caption" component="h2">End time</Typography>
                            <Typography variant="h6" component="h2">{reformatDate(data.endDate)}</Typography>
                        </Paper>
                    </Grid>
                </Grid>
                <div style={{display:"flex", marginTop:"2.5%"}}>
                    <Grid container spacing={3} direction="column" alignContent="center">                        
                        <Grid item xs={3}>
                            <FormControl style={{minWidth: 240}}>
                                <InputLabel style={{marginBottom:"5px"}}>Route</InputLabel>
                                <Select
                                    onChange={e => handleSelect(e)}
                                    value={selected}
                                    disabled={data.status !== "APPROVED"}
                                >
                                    {renderSelect()}
                                </Select>
                            </FormControl>
                        </Grid>
                        {selected !== null && selected !== undefined &&
                        <Grid item xs>
                            {selected.duration ? <p><AccessTime/> <div>{formatDuration(selected.duration)}</div></p> : null}
                            {selected.distance ? <p><DirectionsCar/> <div>{selected.distance}km</div></p> : null}
                        </Grid>
                        }
                        <Grid item xs>
                            <FormGroup row>
                                <FormControlLabel
                                    control={<Checkbox color="primary" name="range" disabled={data.status !== "APPROVED"} checked={checks.range} onChange={handleChange}/>}
                                    label="Driving range"
                                />
                            </FormGroup>
                            <FormGroup row>
                                <FormControlLabel
                                    control={<Checkbox color="primary" name="load" disabled={data.status !== "APPROVED"} checked={checks.load} onChange={handleChange}/>}
                                    label="With load"
                                />
                            </FormGroup>
                        </Grid>
                        {data.status === "PENDING" &&
                        <Grid item xs>
                            <FormGroup row>
                                <Button variant="contained" color="primary" style={{marginRight:"2%"}} onClick={e => setClassStatus('APPROVED')}>Accept</Button>
                                <Button variant="contained" color="primary" onClick={e => setClassStatus('DENIED')}>Decline</Button>
                            </FormGroup>
                        </Grid>
                        }
                        {data.status === "APPROVED" &&
                        <Grid item xs>
                            <FormGroup row>
                                <Button variant="contained" color="primary" onClick={e => handleComplete()}>Complete</Button>
                            </FormGroup>
                        </Grid>
                        }
                    </Grid>
                    <Grid container spacing={3} style={{marginTop:"10px"}}>
                        <div ref={mapRef} className="map" style={{height:"600px", width:"100%"}}/>
                    </Grid>
                </div>                    
            </div>
            <CustomAlert severity={'error'} message={message} show={show} handleClose={e => setShow(false)}/>
        </div>
    )
} 
export default ClassDetails;