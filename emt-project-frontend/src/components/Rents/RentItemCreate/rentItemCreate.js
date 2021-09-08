import React from 'react'
import { useHistory } from 'react-router'

const RentItemCreate = (props) => {

    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        videoGameId: "",
        quantity: 0,
        from: "",
        to: "",
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const videoGameId = formData.videoGameId;
        const quantity = formData.quantity;
        const from = formData.from;
        const to = formData.to;

        props.onAddRentItem(videoGameId, quantity, from, to)
        history.push("/videoGames")
    }

    return (
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                <div className="form-group">
                        <label>Game</label>
                        <select name="videoGameId" className="form-control" onChange={handleChange}>
                            {props.videoGames.map((videoGame) =>
                                <option value={videoGame.id.id}>{videoGame.videoGameName}</option>
                            )}
                        </select>
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
                        <label htmlFor="from">From</label>
                        <input type="datetime-local"
                               className="form-control"
                               id="from"
                               name="from"
                               placeholder="From"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="to">To</label>
                        <input type="datetime-local"
                               className="form-control"
                               id="to"
                               name="to"
                               placeholder="To"
                               required
                               onChange={handleChange}
                        />
                    </div>

                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default RentItemCreate;