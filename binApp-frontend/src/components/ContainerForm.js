import React, { Component, useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router";
import { Button, Container, Form, FormGroup, Input, Label } from "reactstrap";
import ContainerService from "../services/ContainerService";
import {Link} from "react-router-dom";


const ContainerForm = (props) => {

  const [name, setName] = useState("");
  const [width, setWidth] = useState(0);
  const [length, setLength] = useState(0);
  const [height, setHeight] = useState(0);
  const [price, setPrice] = useState(0);
  const [containersAmount, setContainersAmount] = useState(0);
  const navigate = useNavigate();
  const {id} = useParams();
  const isAddMode = !id;

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
        alert("A form was submitted: " + JSON.stringify(item));
        fetch(`http://localhost:8080/containers/${id}`, {
          method: "PUT",
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
          },
          body: JSON.stringify(item),
          })
          .then(function (response) {
            console.log(response);
            return response.json();
            
          })
          .catch((e) => console.log(e));
          window.location.replace("http://localhost:3000/containers/");
      } else {

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
      window.location.replace("http://localhost:3000/containers/");
    }
  };
  
  
  return (
    <div>
      <h1>{isAddMode ? 'Add container': 'Edit container'}</h1>
      <Container>
        <Link to='/containers' className='btn btn-success'>Containers List</Link>
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
              {isAddMode? 'Add container' : 'Edit container'}
            </Button>{" "}
          </FormGroup>
        </Form>
      </Container>
    </div>
  );
};

export default ContainerForm;

