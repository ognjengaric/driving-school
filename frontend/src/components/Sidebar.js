import React, {useEffect, useState} from 'react';
import {Drawer, List, ListItem, ListItemText, ListItemIcon} from '@material-ui/core';
import {makeStyles} from '@material-ui/styles';
import {SchoolRounded, AccountBoxRounded, DirectionsCarRounded, TrafficRounded, CalendarTodayRounded, BookRounded, ScheduleRounded, AssessmentRounded} from '@material-ui/icons';
import {serviceConfig} from '../appSettings.js';
import AlertDialog from './AlertDialog';
import DrivingSchoolForm from './DrivingSchoolForm';
import CustomAlert from './CustomAlert';
import {useHistory} from "react-router";

const useStyles = makeStyles({
    list: {
      width: 250,
    },
    fullList: {
      width: 'auto',
    },
  });

const Sidebar = ({open, handleChange}) => {
    const anchor = "left";
    const classes = useStyles();
    const [displayList, setDisplayList] = useState([]);
    const [exists, setExists] = useState(true);
    const [show, setShow] = useState(false);
    const [showForm, setShowForm] = useState(false);
    const [showAlert, setShowAlert] = useState(false);

    let history = useHistory();

    const adminList = [{title: "Driving school", icon: <SchoolRounded/>, path:'driving-school'}, {title: "Users", icon: <AccountBoxRounded/>}, {title: "Routes", icon: <DirectionsCarRounded/>, path:'routes'}, {title: "Streets", icon: <TrafficRounded/>, path:'streets'}]
    const instructorList = [{title: "Calendar", icon: <CalendarTodayRounded/>}, {title: "Classes", icon: <BookRounded/>}, {title: "Candidates", icon: <AccountBoxRounded/>}]
    const candidateList = [{title: "Scheduling", icon: <ScheduleRounded/>, path:'schedule'}, {title: "Classes", icon: <BookRounded/>}, {title: "Course progress", icon: <AssessmentRounded/>}]

    useEffect(() => {
      const auth = JSON.parse(localStorage.getItem('auth'));

      if(!auth)
        return;

      if(auth.role.toLowerCase().includes('admin')){
        setDisplayList(adminList);
        checkIfSchoolExists();
      }

      if(auth.role.toLowerCase().includes('instructor'))
        setDisplayList(instructorList);

      if(auth.role.toLowerCase().includes('candidate'))
        setDisplayList(candidateList);
        
    // eslint-disable-next-line
    }, []);

    const checkIfSchoolExists = () => {
      
      const auth = JSON.parse(localStorage.getItem('auth'));

      const requestOptions = {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${auth.token}` ,
        }
      }

      fetch(`${serviceConfig.baseURL}/driving-school/exists`, requestOptions)
        .then(response => {
          if (!response.ok) {
              return Promise.reject(response);
          }
          setExists(true)
      })
      .catch(response => {
        if(response.status === 400)
          setExists(false);
      })
    }

    const handleClick = (path) => {
        if(path === 'driving-school' && !exists){
          setShow(true);
          return;
        }

        history.push(`/${path}`);
    }

    const handleAgree = () => {
      setShowForm(true);
      setShow(false);
    }

    const list = () => (
        <div
          className={classes.list}
          role="presentation"
        >
          <List>
            {displayList.map((el) => (
              <ListItem button key={el.title} onClick={() => handleClick(el.path)}>
                <ListItemIcon>{el.icon}</ListItemIcon>
                <ListItemText primary={el.title} />
              </ListItem>
            ))}
          </List>
        </div>
      )

    return(
        <div>
          <Drawer open={open} variant="temporary" anchor={anchor} onClose={(e) => handleChange(e, false)}>
            {list(anchor)}
          </Drawer>
          <AlertDialog show={show} handleDisagree={() => setShow(false)} handleAgree={handleAgree}/>
          <DrivingSchoolForm showDialog={showForm} handleClose={() => setShowForm(false)} showAlert={() => setShowForm(true)} check={checkIfSchoolExists}/>
          <CustomAlert severity={'success'} message={"Successfully added!"} show={showAlert} handleClose={() => setShowAlert(false)}/>
        </div>
    );
}
export default Sidebar;