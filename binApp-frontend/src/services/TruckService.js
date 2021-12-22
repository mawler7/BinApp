import axios from "axios";

const TRUCKS_REST_API_URL = 'http://localhost:8080/trucks/';

class TruckService{

    getTrucks(){
        return axios.get(TRUCKS_REST_API_URL);
    }
}

export default new TruckService();