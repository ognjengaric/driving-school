import React, { useEffect, useState } from 'react';
import { ViewState, EditingState } from '@devexpress/dx-react-scheduler';
import {
  Scheduler,
  DayView,
  WeekView,
  MonthView,
  Appointments,
  Toolbar, 
  ViewSwitcher,
  DateNavigator,
  AllDayPanel, 
  EditRecurrenceMenu,
  ConfirmationDialog, 
  AppointmentForm, 
  AppointmentTooltip
} from '@devexpress/dx-react-scheduler-material-ui';
import HomePage from './HomePage';
import {makeStyles} from '@material-ui/core'
import {serviceConfig} from '../appSettings.js'

const useStyles = makeStyles((theme) => ({
    scheduler: {
        padding: '5%',
        height: '50%'
    }
}));

const SchedulingCalendar = () => {
    const classes = useStyles();
    const [data, setData] = useState([]);

    useEffect(() => {
        fetchData();
    }, [])

    const fetchData = () => {
        const auth = JSON.parse(localStorage.getItem('auth'));
        const requestOptions = {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${auth.token}` 
            }
        }

        fetch(`${serviceConfig.baseURL}/class/appointments`, requestOptions)
        .then(response => {
            if (!response.ok) {
                return Promise.reject(response);
            } 
            return response.json()
        })
        .then(data => {
            setData(formatData(data))
        })
    }

    const formatData = (appointments) => {
        appointments.forEach(a => {
            a.startDate = new Date(a.startDate)
            a.endDate = new Date(a.endDate)
        });
        return appointments;
    }

    return(
        <div>
            <HomePage/>
            <div className={classes.scheduler}>
                <Scheduler style={{padding:"5%"}} data={data}>
                <ViewState/>
                <EditingState/>
                <DayView
                    startDayHour={8}
                    endDayHour={21}
                />
                <WeekView
                    name="work-week"
                    displayName="Work Week"
                    excludedDays={[0, 6]}
                    startDayHour={8}
                    endDayHour={21}
                />
                <MonthView/>
                <EditRecurrenceMenu />
                <Toolbar/>
                <DateNavigator/>
                <ViewSwitcher/>                    
                <Appointments/>  
                <AppointmentTooltip/>   
                <AllDayPanel/>       
                <ConfirmationDialog/>
                <AppointmentForm 
                    
                />
                </Scheduler>
            </div>
        </div>
    )
}
export default SchedulingCalendar;