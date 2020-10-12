import React, {useEffect, useState} from 'react';
import HomePage from './HomePage';
import { FormControl, InputLabel, Select, MenuItem} from '@material-ui/core';
import { DataGrid} from '@material-ui/data-grid';
import {serviceConfig} from '../appSettings.js'
import {useHistory} from "react-router";

const DrivingClasses = () => {
    const rowNumOptions = [5, 10, 25, 100]; 
    let history = useHistory();
    
    const [classes, setClasses] = useState([]);
    const [statuses, setStatuses] = useState([]);
    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(rowNumOptions[0]);
    const [count, setCount] = useState(0);
    const [loading, setLoading] = useState(false);
    const [selected, setSelected] = useState('');


    useEffect(() => {
        fetchClasses();
        // eslint-disable-next-line
    }, [page, rowsPerPage])

    useEffect(() => fetchClassStatuses(), [])

    const fetchClasses = () => {
        const auth = JSON.parse(localStorage.getItem('auth'));
        const requestOptions = {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${auth.token}` 
            }
        }
        setLoading(true);
        fetch(`${serviceConfig.baseURL}/class/my?page=${page}&size=${rowsPerPage}`, requestOptions)
        .then(response => {
            if (!response.ok) {
                return Promise.reject(response);
            } 
            return response.json()
        })
        .then(data => {
            setClasses(formatData(data.content))   
            setCount(data.totalElements)
            setLoading(false);
        })
    }

    const fetchClassStatuses = () => {
        const auth = JSON.parse(localStorage.getItem('auth'));
        const requestOptions = {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${auth.token}` 
            }
        }
        fetch(`${serviceConfig.baseURL}/class-status`, requestOptions)
        .then(response => {
            if (!response.ok) {
                return Promise.reject(response);
            } 
            return response.json()
        })
        .then(data => {
            setStatuses(data)   
        })
    }

    const formatData = (data) => {
        data.forEach(el => {
            el.startDate = new Date(el.startDate).toLocaleString();
            el.endDate = new Date(el.endDate).toLocaleString();            
            el.status = reformatText(el.status);
        })
        return data;
    }

    const columns = [
        { field: 'id', headerName: 'ID' },
        { field: 'instructor', headerName: 'Instructor', width:200},
        { field: 'candidate', headerName: 'Candidate', width:200},
        { field: 'startDate', headerName: 'Start time', width:200},
        { field: 'endDate', headerName: 'End time', width:200},
        { field: 'status', headerName: 'Status'}
    ];

    const renderItems = () => {
        return statuses.map(el =>
            <MenuItem value={el}>{reformatText(el)}</MenuItem>
        )
    }

    const reformatText = (text) => text[0] + text.substring(1, text.length).toLowerCase();  
    
    return (
        <div style={{display:'flex', justifyContent:'center' }}>
            <HomePage/>
            <div style={{ height: 400, width: '75%', marginTop: '5%'}}>
                <div style={{float:'left', marginBottom:'10px'}}>
                    <FormControl style={{minWidth: 120,}}>
                        <InputLabel style={{marginBottom:"5px"}}>Status</InputLabel>
                        <Select
                            value={selected}
                            onChange={e => setSelected(e.target.value)}
                        >
                            {renderItems()}
                        </Select>
                    </FormControl>
                </div>
                <DataGrid 
                    rows={classes} 
                    columns={columns}
                    paginationMode="server"
                    rowsPerPageOptions={rowNumOptions}
                    pageSize={rowsPerPage}
                    onPageSizeChange={e => setRowsPerPage(e.pageSize)} 
                    onPageChange={e => setPage(e.page-1)}
                    pagination
                    rowCount={count}
                    loading={loading}
                    onRowClick={e => history.push(`/class/${e.data.id}`)}
                />
            </div>
        </div>
    )
}
export default DrivingClasses;