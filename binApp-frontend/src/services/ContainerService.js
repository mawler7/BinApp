import axios from "axios";

const CONTAINERS_REST_API_URL = 'http://localhost:8080/containers/';

class ContainerService {

    getContainers(){
        return axios.get(CONTAINERS_REST_API_URL);
    }
}

export default new ContainerService();