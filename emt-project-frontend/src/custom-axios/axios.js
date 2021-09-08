import axios from "axios"

const instance = axios.create({
    baseURL: "http://localhost:9091/api/renter",
    headers: {
        'Access-Control-Allow-Origin': '*'
    }
})

export default instance;