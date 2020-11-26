import React, {useEffect, useState, useRef} from 'react';
import { Grid, List, ListItem, Typography, Button } from "@material-ui/core"
import HomePage from "./HomePage"
import {serviceConfig} from '../appSettings.js'
import {AccessTime, DirectionsCar, Note, Add} from '@material-ui/icons';  
import {Microsoft, loadBingApi} from '../bingMapService.ts';
import {useHistory} from "react-router";

const RoutesView = () => {
    let history = useHistory();
    const mapRef = useRef();
    const [routes, setRoutes] = useState([])
    const [selected, setSelected] = useState(null);
    const [index, setIndex] = useState(0);

    useEffect(() => {
        fetchRoutes();
        // eslint-disable-next-line
    }, [])

    useEffect(() => {
        loadBingApi(serviceConfig.bingApiKey).then(() => {
          initMap();
        });
        // eslint-disable-next-line
      }, [selected]);

      

    const fetchRoutes = () => {
        const auth = JSON.parse(localStorage.getItem('auth'));
        const requestOptions = {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${auth.token}` 
            }
        }

        fetch(`${serviceConfig.baseURL}/route`, requestOptions)
        .then(response => {
            if (!response.ok) {
                return Promise.reject(response);
            } 
            return response.json()
        })
        .then(data => {
            setRoutes(data);
            setSelected(data[0])
        })
    }

    const formatDuration = (duration) => {
        if(duration < 60)
          return duration + 's'
        else
          return `${Math.floor(duration/60)}min ${duration%60 ? duration % 60 + 's' : ''}`;
    }

    const handleClick = (e, i) => {
        setSelected(routes[i])
        setIndex(i);
    }

    const renderItems = () => {
        return routes.map((el, i) => 
            <ListItem 
                divider 
                button 
                style={{display: 'flex', flexDirection: 'column', justifyContent:'center'}} 
                key={el.id} 
                onClick={e => handleClick(e, i)}
                selected={i === index ? true : false}
            >
                <Typography color="textSecondary">#{el.id}</Typography>
                <div style={{display: 'flex', alignItems:'center'}}><AccessTime/><span>{formatDuration(el.duration)}</span></div>
                <div style={{display: 'flex', alignItems:'center'}}><DirectionsCar/> <span>{el.distance}km</span></div>
                <div style={{display: 'flex', alignItems:'center'}}><Note/> <span>{el.category}</span></div>
            </ListItem>
        )
    }

    const initMap = () => {
        const map = new Microsoft.Maps.Map(mapRef.current);
        if(routes.length !== 0)
            drawRoute(map);
        return map;
    }

    const drawRoute = (map) => {
        var locations = Microsoft.Maps.PointCompression.decode(selected.encodedCoordinates);

        var p = new Microsoft.Maps.Polyline(locations, {
            strokeColor: 'red',
            strokeThickness: 5
        });
        map.entities.push(p);
        map.setView({ bounds: Microsoft.Maps.LocationRect.fromLocations(locations) });
    }


    return(
        <div style={{display:'flex', justifyContent:'center' }}>
            <HomePage/>
            <div style={{ width: '90%', marginTop: '5%', display:'flex'}}>
                <Grid container spacing={3}>
                    <Grid item xs={2}>
                        <List component="nav">
                            <Button
                                variant="contained"
                                color="primary"
                                startIcon={<Add/>}
                                style={{marginBottom:"10px", float:"left"}}
                                onClick={() => history.push("/new-route")}
                            >
                                New 
                            </Button>
                            {renderItems()}
                        </List>
                    </Grid>
                    <Grid item xs={10}>
                        <div ref={mapRef} className="map" style={{height:"700px", width:"100%"}}/>
                    </Grid>
                </Grid>
            </div>
        </div>
    )
}
export default RoutesView;