import httpClient from '../http-common'

const getAll = () => {
    return httpClient.get('/containers/');
}

const create = data => {
    return httpClient.post('/containers/', data);
}

const get = id => {
    return httpClient.get(`/containers/${id}`);
}

const update = (id, data) => {
    return httpClient.patch(`/containers/edit/${id}`, id, data)
}


const remove = id => {
    return httpClient.delete(`containers/${id}`, id)
}

export default {getAll, create, get, remove, update }