import React, { useEffect, useState } from 'react';
import HomePage from './HomePage';
import { makeStyles} from '@material-ui/core';
import { DataGrid} from '@material-ui/data-grid';
import {serviceConfig} from '../appSettings.js'

const useStyles = makeStyles((theme) => ({
    container: {
        padding: '5%',
        height: '50%'
    },
    datagrid: {
        width: '75%'
    }
}));

const DrivingSchools = () => {
    const classes = useStyles();
    const rowNumOptions = [5, 10, 25, 100] 
    //const [sortModel, setSortModel] = useState([
      //  {
            //field: 'id',
            //sort: 'asc',
        //}
    //])

    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(rowNumOptions[0]);
    const [schools, setSchools] = useState([]);
    const [count, setCount] = useState(0);
    const [loading, setLoading] = useState(false);
    
    useEffect(() => {
        fetchSchools();
        // eslint-disable-next-line
    }, [page, rowsPerPage])

    const fetchSchools = () => {
        const auth = JSON.parse(localStorage.getItem('auth'));
        const requestOptions = {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${auth.token}` 
            }
        }
        setLoading(true);
        fetch(`${serviceConfig.baseURL}/driving-school?page=${page}&size=${rowsPerPage}`, requestOptions)
        //fetch(`${serviceConfig.baseURL}/driving-school?page=${page}&size=${rowsPerPage}&sort=${sortModel[0].field}&direction=${sortModel[0].sort}`, requestOptions)
        .then(response => {
            if (!response.ok) {
                return Promise.reject(response);
            } 
            return response.json()
        })
        .then(data => {
            setSchools(addCounts(data.content))    
            setCount(data.totalElements)
            setLoading(false);
        })
    }

    const addCounts = (data) => {
        data.forEach(element => {
            element.candidateNum = element.candidates.length;
            element.instructorNum = element.instructors.length;
        });
        return data;
    }

    const columns = [
        { field: 'id', headerName: 'ID' },
        { field: 'name', headerName: 'Name'},
        { field: 'shortName', headerName: 'Short name'},
        {
          field: 'instructorNum',
          headerName: 'Number of instructors',
          type: 'number',
        },
        {
            field: 'candidateNum',
            headerName: 'Number of students',
            type: 'number',
          }
      ];

    return(
        <div style={{display:'flex', justifyContent:'center' }}s>
            <HomePage/>
            <div style={{ height: 400, width: '50%', marginTop: '5%'}}>
                <DataGrid 
                    rows={schools} 
                    columns={columns}
                    paginationMode="server"
                    //sortingMode="server"
                    rowsPerPageOptions={rowNumOptions}
                    pageSize={rowsPerPage}
                    onPageSizeChange={e => setRowsPerPage(e.pageSize)} 
                    onPageChange={e => setPage(e.page-1)}
                    //sortModel={sortModel}
                    //onSortModelChange={e => e.sortModel !== sortModel ? setSortModel(e.sortModel) : null}
                    pagination
                    rowCount={count}
                    loading={loading}
                />
            </div>
        </div>
    )
}
export default DrivingSchools;