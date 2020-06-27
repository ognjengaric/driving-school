import React from 'react';
import {Button, Dialog, DialogTitle, DialogContent, DialogContentText, DialogActions, FormControl, InputLabel, Select, MenuItem, makeStyles} from '@material-ui/core'

const useStyles = makeStyles((theme) => ({
    formControl: {
      marginTop: theme.spacing(1),
    }
  }));

const StreetForm = ({open, handleClose, street, handleChange, handleSubmit}) => {
    const classes = useStyles();

    return(
        <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
            <form onSubmit={handleSubmit}>
                <DialogTitle id="form-dialog-title">{street.name}</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        Edit undefined route type or traffic intensity.
                    </DialogContentText>
                    <FormControl fullWidth className={classes.formControl}>
                        <InputLabel id="type-label">Road Type</InputLabel>
                        <Select
                            labelId="type-label"
                            value={street.roadType}
                            name="roadType"
                            onChange={handleChange}
                        >
                        {['UNDEFINED', 'URBAN', 'RURAL'].map(el => <MenuItem key={el} value={el}>{el.toLowerCase()}</MenuItem>)}
                        </Select>
                    </FormControl>
                    <FormControl fullWidth className={classes.formControl}>
                        <InputLabel id="intensity-label">Traffic intensity</InputLabel>
                        <Select
                            labelId="intensity-label"
                            value={street.intensity}
                            name="intensity"
                            onChange={handleChange}
                        >
                        {['UNDEFINED', 'LOW', 'MEDIUM', 'HIGH'].map(el => <MenuItem key={el} value={el}>{el.toLowerCase()}</MenuItem>)}
                        </Select>
                    </FormControl>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose} color="primary">
                        Cancel
                    </Button>
                    <Button color="primary" type="submit">
                        Save
                    </Button>
                </DialogActions>
          </form>
        </Dialog>
    )
}
export default StreetForm;