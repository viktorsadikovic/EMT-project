package mk.ukim.finki.videogamescatalog.domain.models;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.financial.Money;
import mk.ukim.finki.videogamescatalog.domain.enumerations.Genre;
import mk.ukim.finki.videogamescatalog.domain.enumerations.Platform;
import mk.ukim.finki.videogamescatalog.domain.valueobjects.Quantity;

import javax.persistence.*;

@Entity
@Table(name = "video_game")
@Getter
public class VideoGame extends AbstractEntity<VideoGameId> {

    private String videoGameName;

    private Quantity quantity;

    // Value Object koj sto vo sebe sodrzi valuta(Currency: Enum) i sumata na pari(amount: double)
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "price_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "price_currency"))
    })
    private Money price;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Enumerated(EnumType.STRING)
    private Platform platform;

    private VideoGame() {
        super(VideoGameId.randomId(VideoGameId.class));
    }

    public static VideoGame build(String videoGameName, Money price, Quantity quantity, Genre genre, Platform platform) {
        VideoGame videoGame = new VideoGame();
        videoGame.videoGameName = videoGameName;
        videoGame.quantity = quantity;
        videoGame.price = price;
        videoGame.genre = genre;
        videoGame.platform = platform;

        return videoGame;
    }

    // Ednostaven metod vo ramkite na entitetot koj sluzi za zgolemuvanje na kolicinata na primeroci od igrata
    public void addQuantity(int qty) {
        this.quantity = this.quantity.addQuantity(qty);
    }

    // Ednostaven metod vo ramkite na entitetot koj sluzi za namaluvanje na kolicinata na primeroci od igrata
    public void removeQuantity(int qty) {
        this.quantity = this.quantity.removeQuantity(qty);
    }

    // Ednostaven metod vo ramkite na entitetot koj sluzi za promena na cenata na igrata
    public void changePrice(Money price) {
        this.price = price;
    }
}
