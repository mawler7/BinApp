import React from "react";
import UserService from "../services/UserService";
import Navbar from "./Navbar";

class UserComponent extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            users: []
        };
    }

    componentDidMount() {
        UserService.getUsers().then((response) => {
            this.setState({users: response.data})
        });
    }

    render() {
        return (
            <div>
                <h1 className="text-center">Users list</h1>
                <table className="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <td>User Fist Name</td>
                        <td>User Last Name</td>
                        <td>User email</td>
                        <td>Actions</td>
                    </tr>

                    </thead>
                    <tbody>
                    {
                        this.state.users.map(
                            user =>
                                <tr key={user.id}>
                                    <td>{user.firstName}</td>
                                    <td>{user.lastName}</td>
                                    <td>{user.email}</td>
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

export default UserComponent