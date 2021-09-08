import React from 'react'
import {useHistory} from 'react-router-dom'

const VideoGameEdit = (props) => {

    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        videoGameName: props.videoGame.videoGameName,
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
        const price = formData.price !== 0 ? formData.price : props.videoGame.price.amount;

        props.onChangeVideoGamePrice(props.videoGame.id, price)
        history.push("/videoGames")
    }

    return(
        <div className="row mt-5">
            <div className={"col-md-12"}>
                <h3>Change Video Game Price</h3>
            </div>
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                <div className="form-group">
                        <label htmlFor="videoGameName">Video Game Name</label>
                        <input type="text"
                               className="form-control"
                               id="videoGameName"
                               name="videoGameName"
                               required
                               disabled
                               value={props.videoGame.videoGameName}
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
                               disabled
                               value={props.videoGame.quantity.quantity}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="price">Price</label>
                        <input type="text"
                               className="form-control"
                               id="price"
                               name="price"
                               required
                               value={props.videoGame.price.amount}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Genre</label>
                        <input type="text"
                               className="form-control"
                               id="price"
                               name="price"
                               required
                               disabled
                               value={props.videoGame.genre}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Platform</label>
                        <input type="text"
                               className="form-control"
                               id="platform"
                               name="platform"
                               required
                               disabled
                               value={props.videoGame.platform}
                               onChange={handleChange}
                        />
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>

    )
}

export default VideoGameEdit;