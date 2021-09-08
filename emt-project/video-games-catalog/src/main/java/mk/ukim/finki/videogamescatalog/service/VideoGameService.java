package mk.ukim.finki.videogamescatalog.service;

import mk.ukim.finki.sharedkernel.financial.Money;
import mk.ukim.finki.videogamescatalog.domain.models.VideoGame;
import mk.ukim.finki.videogamescatalog.domain.models.VideoGameId;
import mk.ukim.finki.videogamescatalog.service.form.VideoGameForm;

import java.util.List;

public interface VideoGameService {

    VideoGame findById(VideoGameId id);
    VideoGame createVideoGame(VideoGameForm form);
    VideoGame deleteVideoGame(VideoGameId id);
    VideoGame changePrice(VideoGameId videoGameId, Money price);
    VideoGame rentItemCreated(VideoGameId videoGameId, int quantity);
    VideoGame rentItemRemoved(VideoGameId videoGameId, int quantity);
    List<VideoGame> getAll();
}
