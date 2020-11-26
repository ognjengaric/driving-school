import React, { useEffect, useState } from 'react';
import {serviceConfig} from '../appSettings.js'
import {Doughnut} from 'react-chartjs-2';
import { Grid } from '@material-ui/core';
import HomePage from './HomePage';

const CourseProgress = () => {

    const [charts, setCharts] = useState([]);

    const titles = ['Total', 'Driving range', 'Low intensity', 'Medium intensity', 'High intensity', 'Night', 'Rural', 'Load'];

    useEffect(() => {
        getCourseData();
        // eslint-disable-next-line
    }, [])

    const getCourseData = () => {
        const auth = JSON.parse(localStorage.getItem('auth'));
        const requestOptions = {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${auth.token}` 
            }
        }
        fetch(`${serviceConfig.baseURL}/candidate/course`, requestOptions)
        .then(response => {
            if (!response.ok) {
                return Promise.reject(response);
            } 
            return response.json()
        })
        .then(data => {
          formatData(data);
        })
    }

    const formatData = (data) => {
        let arr = []
        Object.keys(data).forEach((el, i) => {
            let parts = data[el].split("/");

            arr.push({
                labels: [
                    'Done',
                    'Left',
                ],
                datasets: [{
                    data: [Number(parts[0]), Number(parts[1]-parts[0])],
                    backgroundColor: [
                    '#5ED11B',
                    '#C9C9C9',
                    ],
                    hoverBackgroundColor: [
                    '#5ED11B',
                    '#C9C9C9',
                    ]
                }]
            })

            if(i+1 === Object.keys(data).length)
                setCharts(arr)
        })
    }

    const renderCharts = () => {
        if(charts.length !== 0)
            return titles.map((el, i) => {
                let done = charts[i].datasets[0].data[0];
                let total = charts[i].datasets[0].data[1] + charts[i].datasets[0].data[0]; 
                if(total !== 0){
                    return <Grid key={i} item xs={3}>
                                <h2>{el}</h2>
                                <Doughnut data={charts[i]} />
                                {done}/{total}
                            </Grid>
                }
                return null;
        })
    }

    return(
        <div>
            <HomePage/>
            <h1 style={{marginTop:"3%"}}>Course progress</h1>
            <Grid container spacing={3}>
                {renderCharts()}
            </Grid>
        </div>
    )
}
export default CourseProgress;