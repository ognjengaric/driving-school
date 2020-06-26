import React, {useRef, useEffect, useState} from 'react';
import {Microsoft, loadBingApi} from '../bingMapService.ts';
import {serviceConfig} from '../appSettings.js'
import {Card, makeStyles} from '@material-ui/core'
import TrafficRouteForm from './TrafficRouteForm';

const useStyles = makeStyles((theme) => ({
  card: {
    margin: theme.spacing(2),
    padding: theme.spacing(3)
  }
}));

let directionsManager= {}

const BingMap = ({mapOptions}) => {
  const mapRef = useRef();
  const classes = useStyles();
  const [state, setState] = useState({
    routePath: [],
    time: 0,
    distance: 0,
    streets: []
  })

  const initMap = () => {
    const map = new Microsoft.Maps.Map(mapRef.current);
    if (mapOptions) {
      map.setOptions(mapOptions);
    }

    loadDirectionsModule(map);

    return map;
  }

  useEffect(() => {
    loadBingApi(serviceConfig.bingApiKey).then(() => {
      initMap();
    });
    // eslint-disable-next-line
  }, []);

  const loadDirectionsModule = (map) => {
    Microsoft.Maps.loadModule('Microsoft.Maps.Directions', function () {
      //Create an instance of the directions manager.
      directionsManager = new Microsoft.Maps.Directions.DirectionsManager(map);

      //Specify the where to display the input panel
      directionsManager.showInputPanel('directionsPanel');

      Microsoft.Maps.Events.addHandler(directionsManager, 'directionsUpdated', directionsUpdated);
    });
  }

  const directionsUpdated = (e) => {
    let route = directionsManager.getCurrentRoute()
    let streets = []
    let [time, distance] = [0, 0];

    route.routeLegs.forEach(leg => {
      distance += leg.summary.distance;
      time += leg.summary.time;

      leg.itineraryItems.forEach(action => {
          let street = parseStreet(action.formattedText);
          if(street !== "")
            streets.push(street);
        });
    });
    
    streets = [...new Set(streets)]    
    
    setState({time, distance, routePath: route.routePath, streets});
  }

  const parseStreet = (action) => {   
    let i = action.indexOf('<RoadName>')
    let j = action.indexOf('</RoadName>')
    let street = "";

    if(i !== -1 || j !== -1)
      street = action.substring(i+10, j)

    let parts = street.split(' / ')

    if(parts.length !== 1){
      street = parts.reduce((a, b) => a.length > b.length ? a : b)
    }

    return street;
  }

  return(
    <React.Fragment>
      <div className="directionsContainer">
        <Card className={classes.card} variant="outlined">
          <div id="directionsPanel"></div>
          <TrafficRouteForm routeInfo={state} manager={directionsManager}/>
        </Card>
      </div>  
      <div ref={mapRef} className="map" />
    </React.Fragment>
  );
}
export default BingMap;
