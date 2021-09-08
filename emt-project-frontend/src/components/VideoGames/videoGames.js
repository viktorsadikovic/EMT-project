import React, { Component } from "react";
import { Link } from 'react-router-dom';
import VideoGame from '../VideoGames/VideoGame/videoGame'

class VideoGames extends Component{

    render() {
        const videoGames = this.getVideoGames()
        return(
            <div className={"container mm-4 mt-5"}>
                <div className={"row"}>
                        <table className={"table table-striped"}>
                            <thead>
                                <tr>
                                    <th scope={"col"}>Name</th>
                                    <th scope={"col"}>Quantity</th>
                                    <th scope={"col"}>Genre</th>
                                    <th scope={"col"}>Platform</th>
                                    <th scope={"col"}>Price</th>
                                    <th scope={"col"}></th>
                                </tr>
                            </thead>
                            <tbody>
                                {videoGames}
                            </tbody>
                        </table>
                </div>
                <div className="col mb-3">
                            <div className="row">
                                <div className="col-sm-12 col-md-12">
                                    <Link className={"btn btn-block btn-dark"} to={"/videoGames/add"}>Create new Video Game</Link>
                                </div>
                            </div>
                        </div>
            </div>  
        )
    }


    getVideoGames = () => {
        return this.props.videoGames.map((videoGame) => {
            return (
                <VideoGame videoGame={videoGame}
                            onDeleteVideoGame={this.props.onDeleteVideoGame}
                            onEdit={this.props.onEdit}
                            />
            )
        })
    }
}


export default VideoGames;