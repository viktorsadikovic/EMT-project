package mk.ukim.finki.videogamescatalog.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.sharedkernel.financial.Money;
import mk.ukim.finki.videogamescatalog.domain.exceptions.VideoGameNotFoundException;
import mk.ukim.finki.videogamescatalog.domain.models.VideoGame;
import mk.ukim.finki.videogamescatalog.domain.models.VideoGameId;
import mk.ukim.finki.videogamescatalog.domain.repository.VideoGameRepository;
import mk.ukim.finki.videogamescatalog.service.VideoGameService;
import mk.ukim.finki.videogamescatalog.service.form.VideoGameForm;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class VideoGameServiceImpl implements VideoGameService {

    // Poradi toa sto vo ramkite na ovoj ogranicen kontekst imame samo eden entitet VideoGame koj voedno e i Aggregate Root
    // site izmeni koi gi pravime se izvrsuvaat tokmu preku samiot VideoGame objekt
    private final VideoGameRepository videoGameRepository;

    // Ovoj metod od baza ja vrakja igrata so specifiranoto ID
    @Override
    public VideoGame findById(VideoGameId id) {
        return videoGameRepository.findById(id).orElseThrow(VideoGameNotFoundException::new);
    }

    // Ovoj metod ovozmozuva zacuvuvanje na nova igra vo baza
    @Override
    public VideoGame createVideoGame(VideoGameForm form) {
        VideoGame videoGame = VideoGame.build(form.getVideoGameName(), form.getPrice(), form.getQuantity(), form.getGenre(), form.getPlatform());

        return videoGameRepository.save(videoGame);
    }

    // Ovoj metod ovozmozuva brisenje od baza na igrata so specifiranoto ID
    @Override
    public VideoGame deleteVideoGame(VideoGameId id) {
        VideoGame videoGame =  findById(id);

        videoGameRepository.delete(videoGame);

        return videoGame;
    }

    // Ovoj metod ovozmozuva promena na cenata na igrata so specificirano ID
    // Za cena koristime Value Object Money vo koj gi cuvame samata paricna vrednost kako i valutata
    @Override
    public VideoGame changePrice(VideoGameId videoGameId, Money price) {
        VideoGame videoGame = findById(videoGameId);

        videoGame.changePrice(price);
        videoGameRepository.saveAndFlush(videoGame);

        return videoGame;
    }

    // Ovoj metod se koristi pri ispaluvanjeto na event za kreiran nov RentItem od drugiot ogranicen kontekst(RentManagement)
    // Pri fakjanjeto na eventot za kreiran nov RentItem, se koristi ovaa funkcija koja pravi izmena vo kolicinata na
    // primeroci od igrata so specifirano ID
    @Override
    public VideoGame rentItemCreated(VideoGameId videoGameId, int quantity) {
        VideoGame videoGame = findById(videoGameId);

        videoGame.removeQuantity(quantity);
        videoGameRepository.saveAndFlush(videoGame);

        return videoGame;
    }

    // Ovoj metod se koristi pri ispaluvanjeto na event za brisenje na postoecki RentItem od drugiot ogranicen kontekst(RentManagement)
    // Pri fakjanjeto na eventot za izbrisan RentItem, se koristi ovaa funkcija koja pravi izmena vo kolicinata na
    // primeroci od igrata so specifirano ID
    @Override
    public VideoGame rentItemRemoved(VideoGameId videoGameId, int quantity) {
        VideoGame videoGame = findById(videoGameId);

        videoGame.addQuantity(quantity);
        videoGameRepository.saveAndFlush(videoGame);

        return videoGame;
    }

    // Ovoj metod se koristi za povlekuvanje na site igri od baza
    @Override
    public List<VideoGame> getAll() {
        return videoGameRepository.findAll();
    }
}
