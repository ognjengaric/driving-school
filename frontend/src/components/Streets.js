import React, { useEffect, useState } from 'react';
import HomePage from './HomePage';
import { Grid, Chip, makeStyles, TablePagination, IconButton, ListItem, ListItemIcon, ListItemText, ListItemSecondaryAction, List} from '@material-ui/core';
import { LocationCity, DirectionsCar, Edit } from '@material-ui/icons';
import {serviceConfig} from '../appSettings.js'
import StreetForm from './StreetForm';

const useStyles = makeStyles((theme) => ({
    container: {
        padding: '5%',
        height: '50%'
    }, 
    chip: {
        backgroundColor: 'lightgreen',
        margin: theme.spacing(1),
    },
    chip_u: {
        margin: theme.spacing(1),
    },
    item: {
        width: '75%'
    },
    select: {
        minWidth: "150px"
    }
}));

const Streets = () => {
    const classes = useStyles();
    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(10);
    const [streets, setStreets] = useState([]);
    const [count, setCount] = useState(0);
    const [open, setOpen] = useState(false);
    const [streetToEdit, setStreetToEdit] = useState({})

    useEffect(() => {
        fetchStreets();
        // eslint-disable-next-line
    }, [page, rowsPerPage])

    const fetchStreets = () => {
        const auth = JSON.parse(localStorage.getItem('auth'));
        const requestOptions = {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${auth.token}` 
            }
        }

        fetch(`${serviceConfig.baseURL}/street?page=${page}&size=${rowsPerPage}`, requestOptions)
        .then(response => {
            if (!response.ok) {
                return Promise.reject(response);
            } 
            return response.json()
        })
        .then(data => {
            setStreets(data.content)    
            setCount(data.totalElements)
        })
    }

    const showForm = (event, street) => {
        setStreetToEdit(street);
        setOpen(true);
    }

    const renderItems = () => {
        return streets.map(s => 
            <ListItem key={s.name} divider>
                <ListItemIcon>
                    <IconButton aria-label="edit" onClick={(e) => showForm(e, s)}>
                        <Edit/>
                    </IconButton>                    
                </ListItemIcon>
                <ListItemText primary={s.name}/>
                <ListItemSecondaryAction>
                    <Chip
                        icon={<LocationCity />}
                        label={s.roadType.toLowerCase()}                                
                        className={s.roadType === 'UNDEFINED' ? classes.chip_u : classes.chip}
                    />
                    <Chip
                        icon={<DirectionsCar />}
                        label={s.intensity.toLowerCase()}
                        className={s.intensity === 'UNDEFINED' ? classes.chip_u : classes.chip}
                    />
                </ListItemSecondaryAction>
            </ListItem>
        )
    }

    const handleChangePage = (event, newPage) => {
        setPage(newPage);
    };
    
    const handleChangeRowsPerPage = (event) => {
        setRowsPerPage(parseInt(event.target.value, 10));
        setPage(0);
    }

    const handleChange = (event) => {
        const {value, name} = event.target;
        setStreetToEdit({...streetToEdit, [name]:value})
    }

    const handleSubmit = (event) => {
        event.preventDefault();

        const auth = JSON.parse(localStorage.getItem('auth'));
        const requestOptions = {
            method: 'PUT',
            headers: {
                'Authorization': `Bearer ${auth.token}` ,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(streetToEdit)
        }

        fetch(`${serviceConfig.baseURL}/street`, requestOptions)
        .then(response => {
            if (!response.ok) {
                return Promise.reject(response);
            } 
            fetchStreets();
            setOpen(false);
        })
    }

    return(
        <div>
            <HomePage/>
            <Grid 
                container             
                direction="column"
                alignItems="center"
                justify="space-between"
                className={classes.container}
            >
                <Grid item  className={classes.item}>
                    <List>
                        {renderItems()}
                    </List>
                  <TablePagination
                    component="div"
                    count={count}
                    page={page}
                    rowsPerPage={rowsPerPage}
                    onChangePage={handleChangePage}
                    onChangeRowsPerPage={handleChangeRowsPerPage}
                  />
                </Grid>
            </Grid>
            <StreetForm open={open} street={streetToEdit} handleClose={() => setOpen(false)} handleChange={handleChange} handleSubmit={handleSubmit}></StreetForm>
        </div>
    )
}
export default Streets;