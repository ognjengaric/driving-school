import React from 'react';
import {Drawer, List, ListItem, ListItemText, ListItemIcon} from '@material-ui/core';
import {makeStyles} from '@material-ui/styles';
import {InboxRounded} from '@material-ui/icons';

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

    const list = (anchor) => (
        <div
          className={classes.list}
          role="presentation"
        >
          <List>
            {['Inbox', 'Starred', 'Send email', 'Drafts'].map((text, index) => (
              <ListItem button key={text}>
                <ListItemIcon><InboxRounded/></ListItemIcon>
                <ListItemText primary={text} />
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