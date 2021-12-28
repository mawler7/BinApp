import { useEffect, useState } from 'react';
import TruckService from "../services/TruckService";
import { Link } from "react-router-dom";


const TruckComponent = () => {
    const [trucks, setTrucks] = useState([]);
  
    const init = () => {
      TruckService.getAll()
        .then(response => {
          console.log('Printing truck data', response.data);
          setTrucks(response.data);
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
        TruckService.remove(id)
        .then(response =>{
            console.log('Truck deleted successfully', response.data);
        init();
      })
      .catch(error => {
        console.log('Something went wrong', error);
      })
  }

 
        return (
            <div>
                <div className="container">
                <h1 className="text-center table-bordered">Trucks list</h1>
                <Link to='/trucks/new' className='btn btn-success'>Add New</Link>
                <table className="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <td>Registartion Number</td>
                        <td>Type</td>
                        <td>Max Volume</td>
                        <td>Actions</td>
                    </tr>

                    </thead>
                    <tbody>
                    {
                        trucks.map(truck => (
                                <tr key={truck.id}>
                                    <td>{truck.regNumber}</td>
                                    <td>{truck.truckType}</td>
                                    <td>{truck.maxVolume}</td>
                                    <td>
                                        <Link to={`/trucks/edit/${truck.id}`} className='btn btn-primary mb-2'>Edit</Link>
                                        <button className='btn btn-danger' onClick={()=>{handleDelete(truck.id)}}>Delete</button>
                                    </td>
                                </tr>
                        ))
                    }
                    </tbody>
                </table>
            </div>
          </div>
        )
    }
    


export default TruckComponent