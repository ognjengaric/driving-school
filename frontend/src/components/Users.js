import React, { useEffect, useState } from 'react';
import HomePage from './HomePage';
import UsersTable from "./UsersTable";
import UserForm from "./UserForm";
import {serviceConfig} from '../appSettings.js'
import { Add } from '@material-ui/icons';
import { Button} from '@material-ui/core';


const Users = () => {

    const rowNumOptions = [5, 10, 25, 100] 
    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(rowNumOptions[0]);
    const [data, setData] = useState([]);
    const [count, setCount] = useState(0);
    const [loading, setLoading] = useState(false);
    const [showForm, setShowForm] = useState(false);
    const [role, setRole] = useState('');

    const columns = [
        { field: 'id', headerName: 'ID' },
        { field: 'name', headerName: 'Name'},
        { field: 'surname', headerName: 'Surname'},
        {
            field: 'role',
            headerName: 'Role',
            width: 150
        },
        {
          field: 'currentLicence',
          headerName: 'Current Licence',
          type: 'number',
          width: 150
        }
        
      ];

      useEffect(() => {
        fetchUsers();
        // eslint-disable-next-line
    }, [page, rowsPerPage])


    const fetchUsers = () => {
        const auth = JSON.parse(localStorage.getItem('auth'));
        const requestOptions = {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${auth.token}` 
            }
        }
        setLoading(true);
        fetch(`${serviceConfig.baseURL}/user?page=${page}&size=${rowsPerPage}`, requestOptions)
        .then(response => {
            if (!response.ok) {
                return Promise.reject(response);
            } 
            return response.json()
        })
        .then(data => {
            setData(data.content)
            setCount(data.totalElements)
            setLoading(false);
        })
    }

    return(
        <div style={{display:'flex', justifyContent:'center' }}>
            <HomePage/>
            <div style={{ height: 400, width: '50%', marginTop: '5%'}}>
                <div style={{float:'right', marginBottom:'10px'}}>
                    <Button
                        variant="contained"
                        color="primary"
                        startIcon={<Add/>}
                        style={{marginRight:'10px'}}
                        onClick={e => {setShowForm(true); setRole('Instructor')}}
                    >
                        Instructor 
                    </Button>
                    <Button
                        variant="contained"
                        color="primary"
                        startIcon={<Add/>}
                        onClick={e => {setShowForm(true); setRole('Candidate')}}
                    >
                        Candidate 
                    </Button>
                </div>
                <UsersTable 
                    data={data} 
                    columns={columns} 
                    rowNum={rowsPerPage} 
                    rowNumOptions={rowNumOptions}
                    count={count}
                    loading={loading}
                    pageChange={e => setPage(e.page-1)}
                    sizeChange={e => setRowsPerPage(e.pageSize)}
                />
            </div>
            <UserForm showDialog={showForm} handleClose={e => setShowForm(false)} role={role} fetchUsers={fetchUsers}/>
        </div>
    )

}
export default Users;