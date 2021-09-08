import React, { Component } from "react";
import RentItem from "../RentItem/rentItem";

class CompleteRent extends Component{

    render() {
        const rentItems = this.getRentItems()

        return(
            <div className={"container mm-4 mt-5"}>
                <div className={"row"}>
                        <table className={"table table-striped"}>
                            <thead>
                                <tr>
                                    <th scope={"col"}>VideoGameId</th>
                                    <th scope={"col"}>From</th>
                                    <th scope={"col"}>To</th>
                                    <th scope={"col"}>Quantity</th>
                                    <th scope={"col"}>Price</th>
                                    <th scope={"col"}></th>
                                </tr>
                            </thead>
                            <tbody>
                                {rentItems}
                            </tbody>
                        </table>
                </div>
                <div className="col mb-3">
                            <div className="row">
                                <div className="col-sm-12 col-md-12">
                                <a title={"CompleteRent"} 
                                    className={"btn btn-success"} 
                                    onClick={() => this.props.completeRent(this.props.rent.id.id)}> 
                                    Complete Rent
                                    </a>
                                    <a title={"DeleteRent"} 
                                    className={"btn btn-danger"} 
                                    onClick={() => this.props.onDeleteRent(this.props.rent.id.id)}> 
                                    Complete Rent
                                    </a>
                                </div>
                            </div>
                        </div>
            </div>  
        )
    }


    getRentItems = () => {
        let array = Array.from(this.props.rent.rentItemList)
        return array.map((rentItem) => {
            return (
                <RentItem rentItem={rentItem}
                            onDeleteRentItem={this.props.onDeleteRentItem}
                            />
            )
        })
    }
}


export default CompleteRent;