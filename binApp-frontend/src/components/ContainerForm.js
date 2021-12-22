import React, {Component} from 'react';
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';

class ContainerForm extends Component {

    constructor(props) {
        super(props);
        this.state = {
            containerNames: [],
            selectedContainerName: '',
            amountOfOrderedContainers: 0,
            trucks: [],
            selectedTruck: '',
            email: '',
        };
        this.handleTruckChange = this.handleTruckChange.bind(this);
        this.handleEmailChange = this.handleEmailChange.bind(this);
        this.handleContainerAmountChange = this.handleContainerAmountChange.bind(this);
        this.handleContainerNameChange = this.handleContainerNameChange.bind(this);
    }

    async componentDidMount() {
        const containersResponse = await fetch(`http://localhost:8080/containers/`)
        const containers = await containersResponse.json();
        const containerNames = containers.map((value) => value.name)

        const truckResponse = await fetch(`http://localhost:8080/trucks/`)
        const trucks = await truckResponse.json();
        const truckRegNumbers = trucks.map((value) => value.regNumber)

        this.setState({
            containerNames: containerNames,
            selectedContainerName: containerNames[0],
            trucks: truckRegNumbers,
            selectedTruck: truckRegNumbers[0],
        });
    }

    handleContainerNameChange(event) {
        this.setState({selectedContainerName: event.target.value})
    }

    handleEmailChange(event) {
        this.setState({email: event.target.value})
    }

    handleTruckChange(event) {
        this.setState({selectedTruck: event.target.value})
    }

    handleContainerAmountChange(event) {
        this.setState({amountOfOrderedContainers: event.target.value})
    }

    handleSubmit = (event) => {
        event.preventDefault();

        const item = {
            "amountOfOrderedContainers": this.state.amountOfOrderedContainers,
            "container": {
                "name": this.state.selectedContainerName,
            },
            "truck": {
                "regNumber": this.state.selectedTruck
            },
            "user": {
                "email": this.state.email
            }
        }

        alert('A form was submitted: ' + JSON.stringify(item));
        fetch('http://localhost:8080/orders/', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item)
        }).then(function (response) {
            return response.json()
        }).catch(e => console.log(e));
    }

    render() {
        const nameOptions = this.state.containerNames.map((value) => {
            return <option key={value} value={value}>{value}</option>
        })
        const truckOptions = this.state.trucks.map((value) => {
            return <option key={value} value={value}>{value}</option>
        })

        return <div>


            <Container>
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="name">Container name</Label>
                        <Input type="select" name="container" placeholder='Container name' id="container"
                               value={this.state.selectedContainerName}
                               onChange={this.handleContainerNameChange} autoComplete="container">
                            {nameOptions}
                        </Input>
                    </FormGroup>
                    <FormGroup>
                        <Label for="containersAmount">Amount</Label>

                        <Input type="number" name="containersAmount" placeholder='Ordered amount'
                               id="amountOfOrderedContainers" value={this.state.amountOfOrderedContainers}
                               onChange={this.handleContainerAmountChange} autoComplete="containersAmount"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="truck">Truck</Label>
                        <Input type="select" name="truck" placeholder='Registration number' id="truck"
                               value={this.state.selectedTruck}
                               onChange={this.handleTruckChange} autoComplete="truck">
                            {truckOptions}
                        </Input>
                    </FormGroup>
                    <FormGroup>
                        <Label for="email">Email</Label>
                        <Input type="text" name="email" id="email" placeholder='Email' value={this.state.email}
                               onChange={this.handleEmailChange} autoComplete="email"/>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Submit</Button>{' '}
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }

}

export default ContainerForm;