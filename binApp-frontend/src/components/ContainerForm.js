import React, { Component, useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router";
import { Button, Container, Form, FormGroup, Input, Label } from "reactstrap";
import ContainerService from "../services/ContainerService";


const ContainerForm = (props) => {

  const [name, setName] = useState("");
  const [width, setWidth] = useState(0);
  const [length, setLength] = useState(0);
  const [height, setHeight] = useState(0);
  const [price, setPrice] = useState(0);
  const [containersAmount, setContainersAmount] = useState(0);
  const navigate = useNavigate();
  const {id} = useParams();

  useEffect(() => {

    if(id) {
      ContainerService.get(id)
      .then(item => {
        setName(item.data.name);
        setWidth(item.data.width);
        setLength(item.data.length);
        setHeight(item.data.height);
        setPrice(item.data.price);
        setContainersAmount(item.data.containersAmount);
      })
      .catch(error =>{
        console.log('Something went wrong', error);
      })
    }

    (async () => {
        setName(name);
        setWidth(width);
        setLength(length);
        setHeight(height);
        setPrice(price);
        setContainersAmount(containersAmount);
    })();
  }, []);

  const handleChange = (event, setter) => {
      setter(event.target.value)
  }

  const handleSubmit = (event) => {
    event.preventDefault();

    const item = {
        name: name,
        width: width,
        length: length,
        height: height,
        price: price,
        containersAmount: containersAmount,
      };

      if(id){
        ContainerService.update(item)
        .then(response => {
          console.log('Container data updated succesfully', response.data);
          navigate('/');
        })
        .catch(error => {
          console.log('Something went wrong', error);
        })
      } else {
        ContainerService.create(item)
        .then(response => {
          console.log('Container added successfully', response.data)
          navigate('/containers/new');
        })
        .catch(error => {
          console.log('Something went wrong', error);
        })

    alert("A form was submitted: " + JSON.stringify(item));
    fetch("http://localhost:8080/containers/", {
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
    }
  };
  
  
  return (
    <div>
      <h1>Add container</h1>
      <Container>
        <Form onSubmit={handleSubmit}>
          <FormGroup>
            <Label for="name">Container Name</Label>
            <Input
              type="text"
              name="name"
              placeholder="Container Name"
              id="name"
              value={name}
              onChange={(event) => handleChange(event, setName)}
            >
            </Input>
          </FormGroup>
          <FormGroup>
            <Label for="width">Container Width</Label>
            <Input
              type="number"
              name="width"
              id="width"
              value={width}
              onChange={(event) => handleChange(event, setWidth)}
            />
          </FormGroup>
          <FormGroup>
            <Label for="length">Container Length</Label>
            <Input
              type="number"
              name="length"
              id="length"
              value={length}
              onChange={(event) => handleChange(event, setLength) }
            >
            </Input>
          </FormGroup>
          <FormGroup>
            <Label for="height">Container Height</Label>
            <Input
              type="number"
              name="height"
              id="height"
              value={height}
              onChange={(event) => handleChange(event, setHeight) }
            >
            </Input>
          </FormGroup>
          <FormGroup>
            <Label for="price">Container Price</Label>
            <Input
              type="number"
              name="price"
              id="price"
              value={price}
              onChange={(event) => handleChange(event, setPrice) }
            >
            </Input>
          </FormGroup>
          <FormGroup>
            <Label for="containersAmount">Containers Amount</Label>
            <Input
              type="number"
              name="containersAmount"
              id="containersAmount"
              value={containersAmount}
              onChange={(event) => handleChange(event, setContainersAmount) }
            >
            </Input>
          </FormGroup>
          <FormGroup>
            <Button color="primary" type="submit">
              Add container
            </Button>{" "}
          </FormGroup>
        </Form>
      </Container>
    </div>
  );
};

export default ContainerForm;

