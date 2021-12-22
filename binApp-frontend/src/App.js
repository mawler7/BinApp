import './App.css';
import {Route, Routes} from 'react-router-dom'
import ContainerListComponent from './components/ContainerListComponent';
import FooterComponent from './components/FooterComponent';
import UserListComponent from './components/UserListComponent';
import TruckListComponent from './components/TruckListComponent';
import OrderListComponent from './components/OrderListComponent';
import Home from './components/Home';
import ContainerForm from './components/ContainerForm';
import Navbar from './components/Navbar';

function App() {
    return (

        <div className="App">

            <div>
                <Navbar />
                    <Routes>
                        <Route path="/" element={<Home/>}></Route>
                        <Route path="/users" element={<UserListComponent/>}></Route>
                        <Route path="/trucks" element={<TruckListComponent/>}></Route>
                        <Route path="/containers" element={<ContainerListComponent/>}></Route>
                        <Route path="/orders/list/" element={<OrderListComponent/>}></Route>
                        <Route path="/orders/" element={<ContainerForm/>}></Route>
                    </Routes>
                <FooterComponent/>
            </div>
        </div>
    );
}

export default App;
