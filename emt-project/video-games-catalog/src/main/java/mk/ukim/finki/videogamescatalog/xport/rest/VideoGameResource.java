package mk.ukim.finki.videogamescatalog.xport.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.sharedkernel.financial.Currency;
import mk.ukim.finki.sharedkernel.financial.Money;
import mk.ukim.finki.videogamescatalog.domain.enumerations.Genre;
import mk.ukim.finki.videogamescatalog.domain.enumerations.Platform;
import mk.ukim.finki.videogamescatalog.domain.models.VideoGame;
import mk.ukim.finki.videogamescatalog.domain.models.VideoGameId;
import mk.ukim.finki.videogamescatalog.service.VideoGameService;
import mk.ukim.finki.videogamescatalog.service.form.VideoGameForm;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/video-games")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class VideoGameResource {

    private final VideoGameService videoGameService;

    // Ovoj metod vrakja ja vrakja igrata so navedenoto ID
    @GetMapping("/{id}")
    public VideoGame getSingleVideoGame(@PathVariable String id) {
        return videoGameService.findById(VideoGameId.of(id));
    }

    // Ovoj metod gi vrakja site dostapni zhanrovi na igri koi postojat
    @GetMapping("/genres")
    public List<Genre> getGenres() {
        return Arrays.asList(Genre.values());
    }

    // Ovoj metod gi vrakja site dostapni platformi za igrite koi postojat
    @GetMapping("/platforms")
    public List<Platform> getPlatforms() {
        return Arrays.asList(Platform.values());
    }

    // Ovoj metod gi vrakja site kreirani video igri
    @GetMapping("/all")
    public List<VideoGame> getAll() {
        return videoGameService.getAll();
    }

    // Ovoj metod ovozmozuva kreiranje na nova video igra
    @PostMapping("/save")
    public VideoGame createVideoGame(@RequestBody VideoGameForm videoGameForm) {
        return videoGameService.createVideoGame(videoGameForm);
    }

    // Ovoj metod ovozmozuva brisenje na veke postoecka igra so navedenoto ID
    @DeleteMapping("/{id}/delete")
    public VideoGame deleteVideoGame(@PathVariable String id) {
        return videoGameService.deleteVideoGame(VideoGameId.of(id));
    }

    // Ovoj metod ovozmozuva promena na cenata na igrata so navedenoto ID
    @PostMapping("/{id}/change-price")
    public VideoGame changeVideoGamePrice(@PathVariable String id, @RequestBody int price, @RequestBody Currency currency) {
        return videoGameService.changePrice(VideoGameId.of(id), Money.valueOf(currency, price));
    }
}
