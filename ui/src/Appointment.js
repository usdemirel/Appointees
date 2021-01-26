import React, { Component } from 'react';
import Button from '@material-ui/core/Button';
import AppBar from '@material-ui/core/AppBar';
import Drawer from '@material-ui/core/Drawer'
import Dialog from '@material-ui/core/Dialog'
import Divider from '@material-ui/core/Divider'
import MenuItem from '@material-ui/core/MenuItem'
import Card from '@material-ui/core/Card'
import DatePicker from '@material-ui/pickers/DatePicker';
import TextField from '@material-ui/core/TextField'
import Select from '@material-ui/core/Select'
import SnackBar from '@material-ui/core/Snackbar'
import Step from '@material-ui/core/Step';
import Grid from '@material-ui/core/Grid';
import {
  Stepper,
  StepLabel,
  StepContent,
  StepButton
} from '@material-ui/core'
import {
  RadioButton,
  RadioGroup
} from '@material-ui/core'
import { makeStyles } from '@material-ui/core/styles';
import {scheduleAppointment} from './util/APIUtils';
import Alert from 'react-s-alert';


class Appointment extends Component {
    constructor(props) {
        super(props)
        this.state = {
          loading: false, //otherwise loads forever
          navOpen: false,
          confirmationModalOpen: false,
          confirmationTextVisible: false,
          stepIndex: 0,
          appointmentDateSelected: false,
          appointmentMeridiem: 0,
          validEmail: true,
          validPhone: true,
          smallScreen: window.innerWidth < 768,
          confirmationSnackbarOpen: false,
          schedule: [], //otherwise undefined in renderAppointmentTimes
          bookedAppointments:[], //to track booked appointments
          bookedDatesObject: {}, //tracks dates coupled to their slots
          fullDays: [] //to track full days
        }
        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleInputChange(event) {
        const target = event.target;
        const inputName = target.name;        
        const inputValue = target.value;

        this.setState({
            [inputName] : inputValue
        });        
    }

    handleSubmit(event) {
        event.preventDefault();   

        const appointmentRequest = Object.assign({}, this.state);

        scheduleAppointment(appointmentRequest)
        .then(response => {
            // localStorage.setItem(ACCESS_TOKEN, response.accessToken);
            Alert.success("You're successfully schedule an appointment");
            this.props.history.push("/");
            this.props.history.go(0);
        }).catch(error => {
            Alert.error((error && error.message) || 'Oops! Something went wrong. Please try again!');
        });
    }

    render() {
        const { stepIndex, loading, navOpen, smallScreen, confirmationModalOpen, confirmationSnackbarOpen, ...data } = this.state
        const contactFormFilled = data.firstName && data.lastName && data.phone && data.email && data.validPhone && data.validEmail
        
        return (
        <div>
            <section
             style={{
            maxWidth: !smallScreen ? '80%' : '100%',
            margin: 'auto',
            marginTop: !smallScreen ? 20 : 0,
            }}>
          <Card style={{
              padding: '10px 10px 25px 10px',
            //   height: smallScreen ? '100vh' : null
            }}>
                <Stepper
                activeStep={stepIndex}
                //   activeStep={0}
                linear={false}
                orientation="vertical">
                    <Step 
                    //   disabled={loading}
                    disabled={false}
                    >  
                    <StepButton onClick={() => this.setState({ stepIndex: 0 })}>
                        Choose an available day for your appointment
                        </StepButton>
                        <StepContent>
                        <form noValidate>
                        <TextField
                            id="date"
                            label="Appointment Date"
                            type="date"
                            defaultValue="2017-05-24"
                            InputLabelProps={{
                            shrink: true,
                            }}
                        />
                        </form>
                        </StepContent>
                        </Step>
                        <Step 
                        // disabled={ !Number.isInteger(this.state.appointmentSlot) }
                        disabled={false}

                        >
                        <StepButton onClick={() => this.setState({ stepIndex: 1 })}>
                        Choose an available time for your appointment
                        </StepButton>
                        <StepContent>
                        <form noValidate>
                        <TextField
                            id="time"
                            label="Time"
                            type="time"
                            defaultValue="9:00 AM"
                            InputLabelProps={{
                            shrink: true,
                            }}
                        />
                        </form>
                        </StepContent>
                        </Step>
                        <Step 
                        // disabled={ !Number.isInteger(this.state.appointmentSlot) }
                        disabled={false}

                        >
                        <StepButton onClick={() => this.setState({ stepIndex: 2 })}>
                        Share your contact information with us and we'll send you a reminder
                        </StepButton>
                        <StepContent>
                                <Grid container spacing={3}>
                                    <Grid item xs={12} sm={6}>
                                    <TextField
                                        required
                                        id="firstName"
                                        name="firstName"
                                        label="First name"
                                        fullWidth
                                        autoComplete="given-name"
                                    />
                                    </Grid>
                                    <Grid item xs={12} sm={6}>
                                    <TextField
                                        required
                                        id="lastName"
                                        name="lastName"
                                        label="Last name"
                                        fullWidth
                                        autoComplete="family-name"
                                    />
                                    </Grid>
                                    <Grid item xs={12}>
                                    <TextField
                                        id="address1"
                                        name="address1"
                                        label="Address line 1"
                                        fullWidth
                                        autoComplete="shipping address-line1"
                                    />
                                    </Grid>
                                    <Grid item xs={12}>
                                    <TextField
                                        id="address2"
                                        name="address2"
                                        label="Address line 2"
                                        fullWidth
                                        autoComplete="shipping address-line2"
                                    />
                                    </Grid>
                                    <Grid item xs={12} sm={6}>
                                    <TextField
                                        id="city"
                                        name="city"
                                        label="City"
                                        fullWidth
                                        autoComplete="shipping address-level2"
                                    />
                                    </Grid>
                                    <Grid item xs={12} sm={6}>
                                    <TextField id="state" name="state" label="State/Province/Region" fullWidth />
                                    </Grid>
                                    <Grid item xs={12} sm={6}>
                                    <TextField
                                        id="zip"
                                        name="zip"
                                        label="Zip / Postal code"
                                        fullWidth
                                        autoComplete="shipping postal-code"
                                    />
                                    </Grid>
                                    <Grid item xs={12} sm={6}>
                                    <TextField
                                        id="country"
                                        name="country"
                                        label="Country"
                                        fullWidth
                                        autoComplete="shipping country"
                                    />
                                    </Grid>
                                    <Grid item xs={12} sm={6}>
                                    <TextField
                                        id="phone"
                                        name="phone"
                                        label="Phone"
                                        fullWidth
                                        autoComplete="phone"
                                    />
                                </Grid>
                                <Grid item xs={12}>
                                <Button 
                                    variant="contained" 
                                    component="span"
                                    containedPrimary
                                    color="primary"
                                    fullWidth
                                    onClick={() => {
                                        alert('scheduled');
                                    }}
                                    >
                                    Schedule
                                </Button>
                            </Grid>
                            </Grid>
                        </StepContent>
                    </Step>
                </Stepper>
            </Card>    
            </section>
        </div>
        
        )
    }
}
export default Appointment;