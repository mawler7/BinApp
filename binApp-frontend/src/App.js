import './App.css';
import  {Routes, Route} from 'react-router-dom'
import ContainerListComponent from './components/ContainerListComponent';
import FooterComponent from './components/FooterComponent';
import UserListComponent from './components/UserListComponent';
import TruckListComponent from './components/TruckListComponent';
import OrderListComponent from './components/OrderListComponent';
import Home from './components/Home';
import ContainerForm from './components/ContainerForm';


function App() {
  return (

    <div className="App">
      
          <div>
              <div className='container'>
                  <Routes>
                    <Route path= "/" element = {<Home />}></Route>
                    <Route path= "/users" element = {<UserListComponent />}></Route>
                    <Route path= "/trucks" element = {<TruckListComponent />}></Route>
                    <Route path= "/containers" element = {<ContainerListComponent />}></Route>
                    <Route path= "/orders/list/" element = {<OrderListComponent />}></Route>
                    <Route path= "/orders/" element = {<ContainerForm />}></Route>
                  </Routes>
              </div>
            <FooterComponent />
          </div>
    </div>
  );
}

export default App;
