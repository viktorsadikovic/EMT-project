import axios from '../custom-axios/video-games-axios'

const VideoGamesCatalogService = {
    fetchVideoGames: () => {
        return axios.get("/all");
    },
    fetchGenres: () => {
        return axios.get("/genres")
    },
    fetchPlatforms: () => {
        return axios.get("/platforms")
    },
    getVideoGame: (id) => {
        return axios.get(`/${id}`)
    },
    addVideoGame: (videoGameName, quantity, price, genre, platform) => {
        console.log(videoGameName, quantity, price, genre, platform)
        return axios.post("/save", {
            "videoGameName": videoGameName,
            "quantity": {quantity},
            "price": {
                currency: 1,
                amount: price},
            "genre": genre,
            "platform": platform
        })
    },
    deleteVideoGame: (id) => {
        return axios.delete(`/${id}/delete`)
    },
    changeVideoGamePrice: (id, price) => {
        return axios.post(`/${id}/edit`, {
            "price": price,
            "currency": "USD"
        })
    }
}

export default VideoGamesCatalogService;