import httpClient from '../http-common'

const getAll = () => {
    return httpClient.get('/orders/');
}

const create = data => {
    return httpClient.post('/orders/', data);
}

const get = id => {
    return httpClient.get(`/orders/${id}`);
}

const update = (id,data) => {
    return httpClient.patch(`/orders/${id}`, data);
}


const remove = id => {
    return httpClient.delete(`orders/${id}`, id)
}

const confirm = id => {
    return httpClient.post(`orders/deliver/${id}`, id)
}

export default {getAll, create, get, remove, update, confirm }