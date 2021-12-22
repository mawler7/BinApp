import React, { Component, useState, useEffect } from "react";
import { Button, Container, Form, FormGroup, Input, Label } from "reactstrap";

const ContainerForm = (props) => {
  const [containerNameOptions, setContainerNameOptions] = useState([]);
  const [amountOfOrderedContainers, setAmountOfOrderedContainers] = useState(0);
  const [truckOptions, setTruckOptions] = useState([]);
  const [email, setEmail] = useState("");
  const [selectedContainerName, setSelectedContainerName] = useState("");
  const [selectedTruck, setSelectedTruck] = useState("");

  useEffect(() => {
    (async () => {
      const containersResponse = await fetch(
        `http://localhost:8080/containers/`
      );
      const containers = await containersResponse.json();
      const containerNames = containers.map((value) => value.name);

      const truckResponse = await fetch(`http://localhost:8080/trucks/`);
      const trucks = await truckResponse.json();
      const truckRegNumbers = trucks.map((value) => value.regNumber);

      setContainerNameOptions(createOptions(containerNames));
      setSelectedContainerName(containerNames[0]);
      setTruckOptions(createOptions(truckRegNumbers));
      setSelectedTruck(truckRegNumbers[0]);
      setEmail(email);
    })();
  }, []);

  const handleChange = (event, setter) => {
      setter(event.target.value)
  }

  const handleSubmit = (event) => {
    event.preventDefault();

    const item = {
      amountOfOrderedContainers: amountOfOrderedContainers,
      container: {
        name: selectedContainerName,
      },
      truck: {
        regNumber: selectedTruck,
      },
      user: {
        email: email,
      },
    };

    alert("A form was submitted: " + JSON.stringify(item));
    fetch("http://localhost:8080/orders/", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(item),
    })
      .then(function (response) {
        return response.json();
      })
      .catch((e) => console.log(e));
  };
  
   const createOptions = (values) => {
    return values.map((value) => {
      return (
        <option key={value} value={value}>
          {value}
        </option>
      );
    });
  }
  
  return (
    <div>
      <h1>Add order</h1>
      <Container>
        <Form onSubmit={handleSubmit}>
          <FormGroup>
            <Label for="name">Container name</Label>
            <Input
              type="select"
              name="container"
              placeholder="Container name"
              id="container"
              value={selectedContainerName}
              onChange={(event) => handleChange(event, setSelectedContainerName)}
              autoComplete="container"
            >
              {containerNameOptions}
            </Input>
          </FormGroup>
          <FormGroup>
            <Label for="containersAmount">Amount</Label>

            <Input
              type="number"
              name="containersAmount"
              placeholder="Ordered amount"
              id="amountOfOrderedContainers"
              value={amountOfOrderedContainers}
              onChange={(event) => handleChange(event, setAmountOfOrderedContainers)}
              autoComplete="containersAmount"
            />
          </FormGroup>
          <FormGroup>
            <Label for="truck">Truck</Label>
            <Input
              type="select"
              name="truck"
              placeholder="Registration number"
              id="truck"
              value={selectedTruck}
              onChange={(event) => handleChange(event, setSelectedTruck) }
              autoComplete="truck"
            >
              {truckOptions}
            </Input>
          </FormGroup>
          <FormGroup>
            <Label for="email">Email</Label>
            <Input
              type="text"
              name="email"
              id="email"
              placeholder="Email"
              value={email}
              // onChange={handleEmailChange}
              onChange={(event) => handleChange(event, setEmail)}
              autoComplete="email"
            />
          </FormGroup>
          <FormGroup>
            <Button color="primary" type="submit">
              Submit
            </Button>{" "}
          </FormGroup>
        </Form>
      </Container>
    </div>
  );
};

class ContainerForm2 extends Component {
  constructor(props) {
    super(props);
    this.state = {
      containerNames: [],
      selectedContainerName: "",
      amountOfOrderedContainers: 0,
      trucks: [],
      selectedTruck: "",
      email: "",
    };
    // this.handleTruckChange = this.handleTruckChange.bind(this);
    // this.handleEmailChange = this.handleEmailChange.bind(this);
    // this.handleContainerAmountChange =
    //   this.handleContainerAmountChange.bind(this);
    // this.handleContainerNameChange = this.handleContainerNameChange.bind(this);
    this.handleChange = this.handleChange.bind(this)
  }

  async componentDidMount() {
    const containersResponse = await fetch(`http://localhost:8080/containers/`);
    const containers = await containersResponse.json();
    const containerNames = containers.map((value) => value.name);

    const truckResponse = await fetch(`http://localhost:8080/trucks/`);
    const trucks = await truckResponse.json();
    const truckRegNumbers = trucks.map((value) => value.regNumber);

    this.setState({
      containerNames: containerNames,
      selectedContainerName: containerNames[0],
      trucks: truckRegNumbers,
      selectedTruck: truckRegNumbers[0],
    });
  }

  handleChange(event) {
    const stateName = event.target.name;
    console.log('State name\n', stateName);
    // this.setState({ `${stateName}`: event.target.value });
  }

  handleSubmit = (event) => {
    event.preventDefault();

    const item = {
      amountOfOrderedContainers: this.state.amountOfOrderedContainers,
      container: {
        name: this.state.selectedContainerName,
      },
      truck: {
        regNumber: this.state.selectedTruck,
      },
      user: {
        email: this.state.email,
      },
    };

    alert("A form was submitted: " + JSON.stringify(item));
    fetch("http://localhost:8080/orders/", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(item),
    })
      .then(function (response) {
        return response.json();
      })
      .catch((e) => console.log(e));
  };

  createOptions(values) {
    return values.map((value) => {
      return (
        <option key={value} value={value}>
          {value}
        </option>
      );
    });
  }

  render() {
    // const nameOptions = this.state.containerNames.map((value) => {
    //   return (
    //     <option key={value} value={value}>
    //       {value}
    //     </option>
    //   );
    // });
    // const truckOptions = this.state.trucks.map((value) => {
    //   return (
    //     <option key={value} value={value}>
    //       {value}
    //     </option>
    //   );
    // });

    return (
      <div>
        <Container>
          <Form onSubmit={this.handleSubmit}>
            <FormGroup>
              <Label for="name">Container name</Label>
              <Input
                type="select"
                name="selectedContainerName"
                placeholder="Container name"
                id="container"
                value={this.state.selectedContainerName}
                onChange={this.handleChange}
                autoComplete="container"
              >
                {this.createOptions(this.state.containerNames)}
              </Input>
            </FormGroup>
            <FormGroup>
              <Label for="containersAmount">Amount</Label>

              <Input
                type="number"
                name="amountOfOrderedContainers"
                placeholder="Ordered amount"
                id="amountOfOrderedContainers"
                value={this.state.amountOfOrderedContainers}
                onChange={this.handleChange}
                autoComplete="containersAmount"
              />
            </FormGroup>
            <FormGroup>
              <Label for="truck">Truck</Label>
              <Input
                type="select"
                name="selectedTruck"
                placeholder="Registration number"
                id="truck"
                value={this.state.selectedTruck}
                onChange={this.handleChange}
                autoComplete="truck"
              >
                {this.createOptions(this.state.trucks)}
              </Input>
            </FormGroup>
            <FormGroup>
              <Label for="email">Email</Label>
              <Input
                type="text"
                name="email"
                id="email"
                placeholder="Email"
                value={this.state.email}
                onChange={this.handleChange}
                autoComplete="email"
              />
            </FormGroup>
            <FormGroup>
              <Button color="primary" type="submit">
                Submit
              </Button>{" "}
            </FormGroup>
          </Form>
        </Container>
      </div>
    );
  }
}

export default ContainerForm;
