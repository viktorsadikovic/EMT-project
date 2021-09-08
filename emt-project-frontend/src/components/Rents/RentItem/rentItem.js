import React from 'react'


const RentItem = (props) => {
    console.log(props.rentItem)
    return (
        <tr>
            <td>{props.rentItem.videoGameId.id}</td>
            <td>{props.rentItem.dateFrom}</td>
            <td>{props.rentItem.dateTo}</td>
            <td >{props.rentItem.quantity}</td>
            <td >{props.rentItem.itemPrice.amount} {props.rentItem.itemPrice.currency}</td>
            <td>
                <a title={"Delete"} 
                   className={"btn btn-danger"} 
                   onClick={() => props.onDeleteRentItem(props.rentItem.id.id)}> 
                Delete
                </a>
            </td>
        </tr>
    )
}

export default RentItem;