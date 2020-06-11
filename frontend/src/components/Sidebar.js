import React, {useEffect, useState} from 'react';
import {Drawer, List, ListItem, ListItemText, ListItemIcon} from '@material-ui/core';
import {makeStyles} from '@material-ui/styles';
import {SchoolRounded, AccountBoxRounded, DirectionsCarRounded, TrafficRounded, CalendarTodayRounded, BookRounded, ScheduleRounded, AssessmentRounded} from '@material-ui/icons';

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

    const adminList = [{title: "Driving school", icon: <SchoolRounded/>}, {title: "Users", icon: <AccountBoxRounded/>}, {title: "Routes", icon: <DirectionsCarRounded/>}, {title: "Streets", icon: <TrafficRounded/>}]
    const instructorList = [{title: "Calendar", icon: <CalendarTodayRounded/>}, {title: "Classes", icon: <BookRounded/>}, {title: "Candidates", icon: <AccountBoxRounded/>}]
    const candidateList = [{title: "Scheduling", icon: <ScheduleRounded/>}, {title: "Classes", icon: <BookRounded/>}, {title: "Course progress", icon: <AssessmentRounded/>}]

    useEffect(() => {
      const auth = JSON.parse(localStorage.getItem('auth'));

      if(!auth)
        return;

      if(auth.role.toLowerCase().includes('admin'))
        setDisplayList(adminList);

      if(auth.role.toLowerCase().includes('instructor'))
        setDisplayList(instructorList);

      if(auth.role.toLowerCase().includes('candidate'))
        setDisplayList(candidateList);
        
    // eslint-disable-next-line
    }, []);

    const list = (anchor) => (
        <div
          className={classes.list}
          role="presentation"
        >
          <List>
            {displayList.map((el) => (
              <ListItem button key={el.title}>
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
        </div>
    );
}
export default Sidebar;