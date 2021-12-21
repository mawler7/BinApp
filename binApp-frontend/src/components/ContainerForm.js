import React, { Component } from 'react';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';

class ContainerForm extends Component {


    
   emptyItem = {

        amountOfOrderedContainers: '',
        container: {name: ''},
        truck: {regNumber: ''},
        user: {email: ''}
        }

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem,
            containerNames:[],
            selectedContainerName:'',
            amountOfOrderedContainers: 0,
            trucks: [],
            selectedTruck:'',
            email: '',    
        };
        this.handleTruckChange = this.handleTruckChange.bind(this);
        this.handleEmailChange = this.handleEmailChange.bind(this);
        this.handleContainerAmountChange = this.handleContainerAmountChange.bind(this);
        this.handleContainerNameChange = this.handleContainerNameChange.bind(this);
    }

    async componentDidMount() {
        // if(this.props.match.params.id !== 'new') {
        // }
        // const order = await (await fetch(`/orders/`)).json();
        const containersResponse = await fetch(`http://localhost:8080/containers/`)
        const containers = await containersResponse.json();
        const containerNames = containers.map((value)=> value.name)

        const truckResponse = await fetch(`http://localhost:8080/trucks/`)
        const trucks = await truckResponse.json();
        const truckRegNumbers = trucks.map((value) => value.regNumber)

        this.setState({
            containerNames: containerNames,
            selectedContainerName: containerNames[0],
            trucks: truckRegNumbers,
            selectedTruck: truckRegNumbers[0],
        });
        console.log('ORDER\n', containerNames);
    }

    // handleChange(event) {
    //     const target = event.target;
    //     const value = target.value;
    //     const name = target.name;
    //     let item = {...this.state.item};
    //     item[name] = value;
    //     this.setState({item});
    // }

    handleChange(event) {
        let obj = JSON.parse(event.target.value); //object
      }

    handleContainerNameChange(event){
        this.setState({selectedContainerName:event.target.value})
    }  
    handleEmailChange(event){
        this.setState({email:event.target.value})
    }  
    handleTruckChange(event){
        this.setState({selectedTruck:event.target.value})
    }  
    handleContainerAmountChange(event){
        this.setState({amountOfOrderedContainers:event.target.value})
    }  

    async handleSubmit(event) {
        event.preventDefault();
        const {item} = this.state;
    
        await fetch('http://localhost:8080/orders/', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        }).then(resp => resp.json());
    }
    
    render() {
        const nameOptions = this.state.containerNames.map((value) => {return <option value={value}>{value}</option>})
        const truckOptions = this.state.trucks.map((value) => {return <option value={value}>{value}</option>})
        const {item} = this.state;
        const title = <h2>{item.id ? 'Edit Order' : 'Add Order'}</h2>;
    
        console.log(nameOptions);
                
        return <div>
            

            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="name">Container name</Label>
                        <Input type="select" name="container" placeholder='Container name' id="container" value={this.state.selectedContainerName}
                               onChange={this.handleContainerNameChange} autoComplete="container">
                                   {nameOptions}
                               </Input>
                    </FormGroup>
                    <FormGroup>
                        <Label for="containersAmount">Amount</Label>
                        
                        <Input type="number" name="containersAmount"placeholder='Ordered amount' id="amountOfOrderedContainers" value={this.state.amountOfOrderedContainers}
                               onChange={this.handleContainerAmountChange} autoComplete="containersAmount"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="truck">Truck</Label>
                        {/* <select onChange={this.handleChange}>
                            {this.props.listOption.map((option, index) => 
                            <option key={index} 
                                value={JSON.stringify(option)}> 
                                {option.name}
                            </option>
                            )}
                        </select> */}
                        <Input type="select" name="truck" placeholder='Registration number' id="truck" value={this.state.selectedTruck}
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