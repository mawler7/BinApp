import React from "react";
import TruckService from "../services/TruckService";
import Navbar from "./Navbar";

class TruckComponent extends React.Component{

    constructor(props){
        super(props)
        this.state={
            trucks:[]
        }
    }

    componentDidMount(){
        TruckService.getTrucks().then((response) =>{
            this.setState({ trucks: response.data})
        });
    }

    render(){
        return (
            <div>
                <Navbar />
                <br></br>
                <br></br>
                <br></br>
                <br></br>
                <br></br>
                <h1 className="text-center table-bordered">Trucks list</h1>
                <table className ="table table-striped table-bordered">
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
                            this.state.trucks.map(
                                truck =>
                                <tr key={truck.id}>
                                    <td>{truck.regNumber}</td>
                                    <td>{truck.truckType}</td>
                                    <td>{truck.maxVolume}</td>
                                    <td></td>
                                </tr>
                            )
                        }
                    </tbody>
                </table>
                
            </div>
        )
    }
}

export default TruckComponent