import React from 'react'
import { useHistory } from 'react-router'

const RenterCreate = (props) => {

    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        name: "",
        surname: "",
        email: "",
        street: "",
        streetNumber: "",
        city: "",
        country: ""
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name;
        const surname = formData.surname;
        const email = formData.email;
        const street = formData.street;
        const streetNumber = formData.streetNumber;
        const city = formData.city;
        const country = formData.country;

        props.onCreateRenter(name, surname, email, street, streetNumber, city, country)
        history.push("/rent/rentItem/new")
    }

    return (
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Renter Name</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="name"
                               required
                               placeholder="Enter Renter Name"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="surname">Renter Surname</label>
                        <input type="text"
                               className="form-control"
                               id="surname"
                               name="surname"
                               required
                               placeholder="Enter Renter Surname"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="email">Renter Email</label>
                        <input type="email"
                               className="form-control"
                               id="email"
                               name="email"
                               required
                               placeholder="Enter Renter Email"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="street">Renter Street</label>
                        <input type="text"
                               className="form-control"
                               id="street"
                               name="street"
                               required
                               placeholder="Enter Renter Street"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="streetNumber">Renter Street Number</label>
                        <input type="number"
                               className="form-control"
                               id="streetNumber"
                               name="streetNumber"
                               required
                               placeholder="Enter Renter Street Number"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="city">Renter City</label>
                        <input type="text"
                               className="form-control"
                               id="city"
                               name="city"
                               required
                               placeholder="Enter Renter City"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="country">Renter Country</label>
                        <input type="text"
                               className="form-control"
                               id="country"
                               name="country"
                               required
                               placeholder="Enter Renter Country"
                               onChange={handleChange}
                        />
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default RenterCreate;