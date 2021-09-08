package mk.ukim.finki.rentmanagement.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.rentmanagement.domain.model.Genre;
import mk.ukim.finki.rentmanagement.domain.model.Platform;
import mk.ukim.finki.sharedkernel.base.ValueObject;
import mk.ukim.finki.sharedkernel.financial.Money;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class VideoGame implements ValueObject {

    private final VideoGameId id;
    private final String name;
    private final Money price;
    private final int quantity;
    private final Genre genre;
    private final Platform platform;

    public VideoGame(@JsonProperty("id") VideoGameId id,
                     @JsonProperty("videoGameName") String name,
                     @JsonProperty("price") Money price,
                     @JsonProperty("quantity") int quantity,
                     @JsonProperty("genre") Genre genre,
                     @JsonProperty("platform") Platform platform) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.genre = genre;
        this.platform = platform;
    }
}
