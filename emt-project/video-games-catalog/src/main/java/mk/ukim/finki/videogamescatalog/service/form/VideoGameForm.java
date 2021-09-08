package mk.ukim.finki.videogamescatalog.service.form;

import lombok.Data;
import mk.ukim.finki.sharedkernel.financial.Money;
import mk.ukim.finki.videogamescatalog.domain.enumerations.Genre;
import mk.ukim.finki.videogamescatalog.domain.enumerations.Platform;
import mk.ukim.finki.videogamescatalog.domain.valueobjects.Quantity;

import javax.persistence.Enumerated;

// Ovaa klasa se koristi pri prakjanje na podatoci od Front-end do Back-end
@Data
public class VideoGameForm {

    private String videoGameName;
    private Money price;
    private Quantity quantity;
    @Enumerated
    private Genre genre;
    @Enumerated
    private Platform platform;
}
