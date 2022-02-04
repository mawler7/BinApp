import { useEffect, useState } from 'react';
import OrderService from "../services/OrderService";
import { Link } from "react-router-dom";

const OrderListComponent = () => {

    const [orders, setOrders] = useState([]);
  
    const init = () => {
        OrderService.getAll()
        .then(response => {
          console.log('Printing all orders', response.data);
          setOrders(response.data);
        })
        .catch(error => {
          console.log('Something went wrong', error);
        }) 
    }
  
    useEffect(() => {
      init();
    }, []);

    const handleDelete = id => {
        console.log('Printing id', id);
        OrderService.remove(id)
        .then(response =>{
            console.log('Order deleted successfully', response.data);
        init();
      })
      .catch(error => {
        console.log('Something went wrong', error);
      })
  }

    const handleConfirm = id => {
        console.log('Printing id', id);
        OrderService.confirm(id)
        .then(response =>{
            console.log('Order confirmed successfully', response.data);
        init();
     })
    .catch(error => {
        console.log('Something went wrong', error);
    })
}

        return (
            
                <div className="container">
                <h1 className="text-center table-bordered">Order list</h1>
                <table className="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <td>Container Name</td>
                        <td>Amount</td>
                        <td>Truck Type</td>
                        <td>Reg Number</td>
                        <td>User Full Name</td>
                        <td>Order Date</td>
                        <td>Delivery Date</td>
                        <td>Actions</td>
                    </tr>

                    </thead>
                    <tbody>
                    {
                        orders.map(order => (
                                <tr key={order.id}>
                                    <td>{order.container.name}</td>
                                    <td>{order.amountOfOrderedContainers}</td>
                                    <td>{order.truck.truckType}</td>
                                    <td>{order.truck.regNumber}</td>
                                    <td>{order.user.lastName} {order.user.firstName}</td>
                                    <td>{order.date}</td>
                                    <td>{order.dateDelivered}</td>
                                    <td>
                                        <button className='btn btn-success'  onClick={()=>{handleConfirm(order.id)}} >Confirm</button>
                                        <Link to={`/orders/${order.id}`} className='btn btn-warning'>Edit</Link>
                                        <button className='btn btn-danger' onClick={()=>{handleDelete(order.id)}}>Delete</button>
                                    </td>
                                </tr>
                        ))
                    }
                    </tbody>
                </table>
            </div>
        );
    }


export default OrderListComponent