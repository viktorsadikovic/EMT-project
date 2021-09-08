package mk.ukim.finki.videogamescatalog.domain.valueobjects;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.base.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Quantity implements ValueObject {
    private final int quantity;

    protected Quantity() {
        this.quantity = 0;
    }

    public Quantity(int qty){
        this.quantity = qty;
    }

    // Zgolemuvanje na kolicinata i vrakjanje na nov Quantity Value Object
    public Quantity addQuantity(int qty) {
        return new Quantity(this.quantity + qty);
    }

    // Namaluvanje na kolicinata i vrakjanje na nov Quantity Value Object
    public Quantity removeQuantity(int qty) {
        return new Quantity(this.quantity - qty);
    }
}
