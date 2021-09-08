import { Component } from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import './App.css';
import VideoGames from '../VideoGames/videoGames'
import Header from '../Header/header'
import VideoGamesCatalogService from '../../repository/videoGamesCatalogRepository';
import VideoGameAdd from '../VideoGames/VideoGameAdd/videoGameAdd';
import VideoGameEdit from '../VideoGames/VideoGameEdit/videoGameEdit';
import RentManagementService from '../../repository/rentManagementRepository';
import RenterCreate from '../Rents/RenterCreate/renterCreate';
import RentItemCreate from '../Rents/RentItemCreate/rentItemCreate';
import CompleteRent from '../Rents/CompleteRent/completeRent';

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
     videoGames: [],
     genres: [],
     platforms: [],
     selectedVideoGame: {},
     renter: {},
     rentId: null,
     currentRent: {}
    }
  }

  render() {
    return(
      <div>
        <Router>
          <Header/>
          <main>
            <div className={"container"}>
              <Route path={"/videoGames/add"} exact render={() => <VideoGameAdd platforms={this.state.platforms} 
                                                                              genres={this.state.genres}
                                                                              onAddVideoGame={this.addVideoGame}
                                                                               />}/>
              <Route path={"/videoGames/edit/:id"} exact render={() => <VideoGameEdit onChangeVideoGamePrice={this.changeVideoGamePrice}
                                                                                      videoGame={this.state.selectedVideoGame}/>} />
              <Route path={"/videoGames"} exact render={() => <VideoGames videoGames={this.state.videoGames}
                                                            onDeleteVideoGame={this.deleteVideoGame}
                                                            onEdit={this.getVideoGame}/>}/>
              <Route path={"/renter/new"} exact render={() => <RenterCreate onCreateRenter={this.createRenter}/> } />
              <Route path={"/rent/rentItem/new"} exact render={() => <RentItemCreate onAddRentItem={this.addRentItem}
                                                                                      videoGames={this.state.videoGames} />} />
              <Route path={"/rent/current"} exact render={() => <CompleteRent rent={this.state.currentRent}
                                                                              onDeleteRentItem={this.deleteRentItem}
                                                                              onDeleteRent={this.cancelRent}
                                                                              completeRent={this.completeRent} /> }/>
            </div>
          </main>
        </Router>
      </div>
    )
  }

  loadVideoGames = () => {
    VideoGamesCatalogService.fetchVideoGames()
      .then((data) => {
        console.log(data.data)
        this.setState({
          videoGames: data.data
        })
      })
  }

  loadPlatforms = () => {
    VideoGamesCatalogService.fetchPlatforms()
      .then((data) => {
        console.log(data.data)
        this.setState({
          platforms: data.data
        })
      })
  }

  loadGenres = () => {
    VideoGamesCatalogService.fetchGenres()
      .then((data) => {
        console.log(data.data)
        this.setState({
          genres: data.data
        })
      })
  }

  getVideoGame = (id) => {
    VideoGamesCatalogService.getVideoGame(id)
      .then((data) => {
        console.log(data.data)
        this.setState({
          selectedVideoGame: data.data
        })
      })
  }

  getRenter = (id) => {
    RentManagementService.getRenter(id)
      .then((data) => {
        this.setState({
          renter: data.data
        })
        console.log(this.state.renter)
      })
  }

  createRenter = (name, surname, email, street, streetNumber, city, country) => {
    RentManagementService.createRenter(name, surname, email, street, streetNumber, city, country)
      .then((data) => {
        this.getRenter(data.data.id.id)
      })
  }

  addRentItem = (videoGameId, quantity, from, to) => {
    VideoGamesCatalogService.getVideoGame(videoGameId)
      .then((data) => {
        this.setState({
          selectedVideoGame: data.data
        })
        RentManagementService.addRentItem(this.state.renter.id.id, null, this.state.selectedVideoGame, quantity, from, to)
      .then((data) => {
        console.log(data.data)
        this.setState({
          currentRent: data.data
        })
      })
      })
  }

  deleteRentItem = (rentItemId) => {
    RentManagementService.deleteRentItem(this.state.renter.id.id, this.state.currentRent.id.id, rentItemId)
      .then((data) => {
        this.setState({
          currentRent: data.data
        })
      })
  }

  completeRent = (rentId) => {
    RentManagementService.completeRent(this.state.renter.id.id, rentId)
      .then((data) => {
        console.log(data)
      })
  }

  cancelRent = (rentId) => {
    RentManagementService.removeRent(this.state.renter.id.id, rentId)
      .then((data) => {
        this.setState({
          currentRent: null
        })
      })
  }

  extendReduceRent = (renterId, rentItemId, from, to) => {
    RentManagementService.extendReduceRent(renterId, rentItemId, from, to)
      .then((data) => {
        this.setState({
          currentRent: data.data
        })
      })
  }

  addVideoGame = (videoGameName, quantity, price, genre, platform) => {
    VideoGamesCatalogService.addVideoGame(videoGameName, quantity, price, genre, platform)
      .then(() => {
        this.loadVideoGames()
      })
  }

  deleteVideoGame = (id) => {
    VideoGamesCatalogService.deleteVideoGame(id)
      .then(() => {
        this.loadVideoGames()
      })
  }

  changeVideoGamePrice = (id, price) => {
    VideoGamesCatalogService.changeVideoGamePrice(id, price)
      .then(() => {
        this.loadVideoGames()
      })
  }

  componentDidMount(){
    this.loadVideoGames()
    this.loadGenres()
    this.loadPlatforms()
  }
}

export default App;
