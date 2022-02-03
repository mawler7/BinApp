import './App.css';
import {Route, Routes} from 'react-router-dom'
import ContainerListComponent from './components/ContainerListComponent';
import FooterComponent from './components/FooterComponent';
import UserListComponent from './components/UserListComponent';
import TruckListComponent from './components/TruckListComponent';
import OrderListComponent from './components/OrderListComponent';
import Home from './components/Home';
import Order from './components/Order';
import Navbar from './components/Navbar';
import Container from './components/Container';
import Truck from './components/Truck';
import TruckForm from './components/TruckForm';

function App() {
    return (

        <div className="App">

            <div>
                <Navbar />
                    <Routes>
                        <Route path="/" element={<Home/>}></Route>
                        <Route path="/users" element={<UserListComponent/>}></Route>
                        <Route path="/trucks" element={<TruckListComponent/>}></Route>
                        <Route path="/trucks/new/" element={<Truck/>}></Route>
                        <Route path="/containers" element={<ContainerListComponent/>}></Route>
                        <Route path="/orders/list/" element={<OrderListComponent/>}></Route>
                        <Route path="/orders/" element={<Order/>}></Route>
                        <Route path="/orders/:id" element={<Order/>}></Route>
                        <Route path="/containers/new/" element={<Container/>}></Route>
                        <Route path="/containers/edit/:id" element={<Container/>}></Route>
                        <Route path="/trucks/:id" element={<Truck/>}></Route>
                    </Routes>
                <FooterComponent/>
            </div>
        </div>
    );
}

export default App;
