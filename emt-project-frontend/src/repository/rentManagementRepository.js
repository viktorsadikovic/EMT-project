import axios from '../custom-axios/axios'

const RentManagementService = {
    fetchRents: () => {
        return axios.get("/rents");
    },
    getRenter: (id) => {
        return axios.get(`/${id}`)
    },
    createRenter: (name, surname, email, street, streetNumber, city, country) => {
        return axios.post(`/new`, {
            "renterName": {
                name: name,
                surname: surname
            },
            "email": email,
            "address": {
                street: street,
                streetNumber: streetNumber,
                city: city,
                country: country
            },
            "rentList": []
        })
    },
    editRenter: (id, renter) => {
        return axios.post(`/${id}/edit`, {
            "renter": renter
        })
    },
    addRentItem: (renterId, rentId, videoGame, quantity, from, to) => {
        console.log(videoGame)
        return axios.post(`/${renterId}/rent/${rentId}/add-rent-item`, {
            "videoGame": {
                id: videoGame.id,
                videoGameName: videoGame.videoGameName,
                price: videoGame.price,
                quantity: videoGame.quantity.quantity,
                genre: videoGame.genre,
                platform: videoGame.platform
            },
            "quantity": quantity,
            "dateFrom": from,
            "dateTo": to
        })
    },
    deleteRentItem: (renterId, rentId, rentItemId) => {
        return axios.delete(`/${renterId}/rent/${rentId}/delete-rent-item/${rentItemId}`)
    },
    removeRent: (renterId, rentId) => {
        return axios.delete(`/${renterId}/rent/${rentId}/delete`)
    },
    completeRent: (renterId, rentId) => {
        return axios.post(`/${renterId}/rent/${rentId}/complete`, {
        })
    },
    extendReduceRent: (renterId, rentItemId, from, to) => {
        return axios.post(`/${renterId}/extend-reduce/${rentItemId}`, {
            "from": from,
            "to": to
        })
    }

}

export default RentManagementService;