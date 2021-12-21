import React from "react";
import OrderService from "../services/OrderService";
import Navbar from "./Navbar";

class OrderListComponent extends React.Component{

    constructor(props){
        super(props)
        this.state={
            orders:[]
        }
    }

    componentDidMount(){
        OrderService.getOrders().then((response) =>{
            this.setState({ orders: response.data})
        });
    }

    render(){
        return(
            <div>
                 <Navbar />
                 <br></br>
                 <br></br>
                 <br></br>
                 <br></br>
                 <br></br>
                <h1 className="text-center table-bordered">Order list</h1>
                <table className="table table-striped table-bordered">
                    <thead>
                        <tr>
                            <td>Container Name</td>
                            <td>Amount</td>
                            <td>Truck Type</td>
                            <td>Reg Number</td>
                            <td>User First Name</td>
                            <td>User Last Name</td>
                        </tr>

                    </thead>
                    <tbody>
                        {
                            this.state.orders.map(
                                order => 
                                <tr key={order.id}>
                                    <td>{order.container.name}</td>
                                    <td>{order.amountOfOrderedContainers}</td>
                                    <td>{order.truckType}</td>
                                    <td>{order.regNumber}</td>
                                    <td>{order.firstName}</td>
                                    <td>{order.lastName}</td>
                                </tr>
                            )
                        }
                    </tbody>
                </table>
            </div>
        )
    }
}

export default OrderListComponent