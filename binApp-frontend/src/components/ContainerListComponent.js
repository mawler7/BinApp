import { useEffect, useState } from 'react';
import ContainerService from "../services/ContainerService";
import { Link } from "react-router-dom";

const ContainerComponent = () => {
    const [containers, setContainers] = useState([]);
    const init = () => {
        ContainerService.getAll()
        .then(response => {
            console.log('Container data', response.data);
            setContainers(response.data);
        })
        .catch(error =>{
            console.log('Something went wrong', error);
        })   
    }
    useEffect(() => {
        init();
      }, []);
  
      const handleDelete = id => {
          console.log('Printing id', id);
          ContainerService.remove(id)
          .then(response =>{
              console.log('Container deleted successfully', response.data);
          init();
        })
        .catch(error => {
          console.log('Something went wrong', error);
        })
}

    return (
        <div>
            <div className="container">
            <h1 className="text-center">Containers list</h1>
            <Link to='/containers/new' className='btn btn-success'>Add New</Link>
            <table className="table table-striped table-bordered">
                <thead>
                <tr>
                    <td>Container Name</td>
                    <td>Container Width</td>
                    <td>Container Length</td>
                    <td>Container Height</td>
                    <td>Container Price</td>
                    <td>Container Amount</td>
                    <td>Actions</td>
                </tr>
                </thead>
                <tbody>
                {
                    containers.map(container => (
                            <tr key={container.id}>
                                <td>{container.name}</td>
                                <td>{container.width}</td>
                                <td>{container.length}</td>
                                <td>{container.height}</td>
                                <td>{container.price}</td>
                                <td>{container.containersAmount}</td>
                                <td>
                                    <Link to={`/containers/edit/${container.id}`} className='btn btn-primary mb-2'>Edit</Link>
                                    <button className='btn btn-danger' onClick={()=>{handleDelete(container.id)}}>Delete</button>
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
export default ContainerComponent