import React, {useRef, useEffect} from 'react';
import {Microsoft, loadBingApi} from '../bingMapService.ts';
import {serviceConfig} from '../appSettings.js'

const BingMap = ({mapOptions}) => {
  const mapRef = useRef();

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
      let directionsManager = new Microsoft.Maps.Directions.DirectionsManager(map);

      //Specify where to display the route instructions.
      directionsManager.setRenderOptions({ itineraryContainer: '#directionsItinerary' });

      //Specify the where to display the input panel
      directionsManager.showInputPanel('directionsPanel');
    });
  }

  return(
    <div ref={mapRef} className="map" />
  );
}
export default BingMap;
