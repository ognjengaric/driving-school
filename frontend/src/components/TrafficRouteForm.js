import React, { useState, useEffect } from 'react';
import {makeStyles, Button, Select, FormControl, InputLabel, Grid, MenuItem} from '@material-ui/core'
import {serviceConfig} from '../appSettings.js'
import CustomAlert from './CustomAlert'


const useStyles = makeStyles((theme) => ({
    formControl: {
      marginTop: theme.spacing(1),
      minWidth: "150px"
    }, 
  }));

const TrafficRouteForm = ({routeInfo}) => {
    const classes = useStyles();
    const [category, setCategory] = useState('');
    const [categories, setCategories] = useState([]);
    const [show, setShow] = useState(false);

    const message = 'Route successfully saved!';

    useEffect(() => {
        fetchCategories();
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

    const renderSelectOptions = () => {
        return categories.map(cat => <MenuItem key={cat} value={cat}>{cat}</MenuItem>)
    }

    const handleChange = (event) => {
        setCategory(event.target.value);
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        
        const body = {...routeInfo, category};
        const auth = JSON.parse(localStorage.getItem('auth'));
        const requestOptions = {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${auth.token}`,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(body)
        }

        fetch(`${serviceConfig.baseURL}/route`, requestOptions)
        .then(response => {
            if (!response.ok) {
                return Promise.reject(response);
            }
            setShow(true);
        })
    }

    return(
        <form onSubmit={handleSubmit}>
            <Grid container spacing={3}>
                <Grid item xs={12}>
                    <FormControl  className={classes.formControl}>
                        <InputLabel id="cat-label">Vehicle category</InputLabel>
                        <Select
                            labelId="cat-label"
                            value={category}
                            onChange={handleChange}
                            required
                        >
                            {renderSelectOptions()}
                        </Select>
                    </FormControl>
                </Grid>
                <Grid item xs={12}>
                    <Button variant="contained" color="primary" type="submit">
                        Save
                    </Button>
                </Grid>
            </Grid>
            <CustomAlert severity={'success'} message={message} show={show} handleClose={() => setShow(false)}/>
        </form>
    )
}
export default TrafficRouteForm;