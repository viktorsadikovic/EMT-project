import React from 'react'
import { Link } from 'react-router-dom';


const videoGame = (props) => {
    return (
        <tr>
            <td>{props.videoGame.videoGameName}</td>
            <td>{props.videoGame.quantity.quantity}</td>
            <td>{props.videoGame.genre}</td>
            <td >{props.videoGame.platform}</td>
            <td >{props.videoGame.price.amount} {props.videoGame.price.currency}</td>
            <td>
                <a title={"Delete"} 
                   className={"btn btn-danger"} 
                   onClick={() => props.onDeleteVideoGame(props.videoGame.id.id)}> 
                Delete
                </a>
                <Link className={"btn btn-primary ml-2"} 
                  onClick={() => props.onEdit(props.videoGame.id.id)} 
                  to={`/videoGames/edit/${props.videoGame.id.id}`}>Edit</Link>
            </td>
        </tr>
    )
}

export default videoGame;