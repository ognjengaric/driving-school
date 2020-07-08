import React from 'react';
import {serviceConfig} from '../appSettings.js'
import { saveAs } from 'file-saver';
import {Menu, MenuItem} from '@material-ui/core';

const Reports = ({anchorEl, handleClose}) => {

    const fetchPdf = (event, resource) => {
        
        const auth = JSON.parse(localStorage.getItem('auth'));
        const requestOptions = {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${auth.token}` 
            }
        }

        fetch(`${serviceConfig.baseURL}/report/${resource}`, requestOptions)
        .then(response => {
            if (!response.ok) {
                return Promise.reject(response);
            }
           return response.blob()
        })
        .then(data => {
            handleClose();
            const pdfBlob = new Blob([data],  { type: 'application/pdf' })
            saveAs(pdfBlob, `${resource}.pdf`)
        })
    }

    return(      
        <Menu
            id="simple-menu"
            anchorEl={anchorEl}
            keepMounted
            open={Boolean(anchorEl)}
            onClose={handleClose}
        >
        <MenuItem onClick={(e) => fetchPdf(e, 'users')}>Users</MenuItem>
        <MenuItem onClick={(e) => fetchPdf(e, 'classes')}>Classes</MenuItem>
        <MenuItem onClick={(e) => fetchPdf(e, 'routes')}>Routes</MenuItem>
        <MenuItem onClick={(e) => fetchPdf(e, 'streets')}>Streets</MenuItem>
      </Menu>
    )
} 
export default Reports;