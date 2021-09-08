import React from 'react'
import { useHistory } from 'react-router'

const VideoGameAdd = (props) => {

    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        videoGameName: "",
        quantity: 0,
        price: 0,
        genre: "",
        platform: ""
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const videoGameName = formData.videoGameName;
        const quantity = formData.quantity;
        const price = formData.price;
        const genre = formData.genre;
        const platform = formData.platform;

        props.onAddVideoGame(videoGameName, quantity, price, genre, platform)
        history.push("/videoGames")
    }

    return (
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="videoGameName">Video Game Name</label>
                        <input type="text"
                               className="form-control"
                               id="videoGameName"
                               name="videoGameName"
                               required
                               placeholder="Enter Video Game Name"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="quantity">Quantity</label>
                        <input type="text"
                               className="form-control"
                               id="quantity"
                               name="quantity"
                               placeholder="Quantity"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="price">Price</label>
                        <input type="text"
                               className="form-control"
                               id="price"
                               name="price"
                               placeholder="Price"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Genre</label>
                        <select name="genre" className="form-control" onChange={handleChange}>
                            {props.genres.map((genre) =>
                                <option value={genre}>{genre}</option>
                            )}
                        </select>
                    </div>
                    <div className="form-group">
                        <label>Platform</label>
                        <select name="platform" className="form-control" onChange={handleChange}>
                            {props.platforms.map((platform) =>
                                <option value={platform}>{platform}</option>
                            )}
                        </select>
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default VideoGameAdd;