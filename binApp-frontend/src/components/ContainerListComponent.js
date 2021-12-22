import React from "react";
import ContainerService from "../services/ContainerService";
import Navbar from "./Navbar";

class ContainerComponent extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            containers: []
        }
    }

    componentDidMount() {
        ContainerService.getContainers().then((response) => {
            this.setState({containers: response.data})
        });
    }

    render() {
        return (
            <div>
                <h1 className="text-center">Containers list</h1>
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
                        this.state.containers.map(
                            container =>
                                <tr key={container.id}>
                                    <td>{container.name}</td>
                                    <td>{container.width}</td>
                                    <td>{container.length}</td>
                                    <td>{container.height}</td>
                                    <td>{container.price}</td>
                                    <td>{container.containersAmount}</td>
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

export default ContainerComponent