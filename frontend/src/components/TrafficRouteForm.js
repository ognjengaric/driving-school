import React, { useState, useEffect } from 'react';
import {makeStyles, Button, Select, FormControl, InputLabel, Grid, MenuItem} from '@material-ui/core'
import {serviceConfig} from '../appSettings.js'


const useStyles = makeStyles((theme) => ({
    formControl: {
      marginTop: theme.spacing(1),
      minWidth: "150px"
    }, 
  }));

const TrafficRouteForm = () => {
    const classes = useStyles();
    const [category, setCategory] = useState('');

    const [categories, setCategories] = useState([]);

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

        fetch(`${serviceConfig.baseURL}/driving-school/categories`, requestOptions)
        .then(response => {
            if (!response.ok) {
                return Promise.reject(response);
            } 
            return response.json()
        })
        .then(data => setCategories(data))
    }

    const renderSelectOptions = () => {
        return categories.map(cat => <MenuItem value={cat}>{cat}</MenuItem>)
    }

    const handleChange = (event) => {
        setCategory(event.target.value);
    };

    return(
        <form>
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
        </form>
    )
}
export default TrafficRouteForm;