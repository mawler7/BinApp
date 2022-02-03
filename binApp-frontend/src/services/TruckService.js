import httpClient from '../http-common'

const getAll = () => {
    return httpClient.get('/trucks/');
}

const create = data => {
    return httpClient.post('/trucks/', data);
}

const get = id => {
    return httpClient.get(`/trucks/${id}`);
}

const update = (id, data) => {
    return httpClient.put(`/trucks/${id}`, data)
}


const remove = id => {
    return httpClient.delete(`trucks/${id}`, id)
}

export default {getAll, create, get, remove, update }