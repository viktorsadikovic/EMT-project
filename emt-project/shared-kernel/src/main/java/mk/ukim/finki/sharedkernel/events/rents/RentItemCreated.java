package mk.ukim.finki.sharedkernel.events.rents;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.config.TopicHolder;
import mk.ukim.finki.sharedkernel.events.DomainEvent;

// Event koj se ispaluva od strana na RentManagement ograniceniot kontekst i se fakja vo VideoGamesCatalog ograniceniot kontekst
// pri kreiranje na nov RentItem so cel da se promeni kolicinata na dostapni primeroci od dadenata igra
@Getter
public class RentItemCreated extends DomainEvent {

    private String productId;
    private int quantity;

    public RentItemCreated(String topic) {
        super(TopicHolder.TOPIC_RENT_ITEM_CREATED);
    }

    public RentItemCreated(String productId, int quantity) {
        super(TopicHolder.TOPIC_RENT_ITEM_CREATED);
        this.productId = productId;
        this.quantity = quantity;
    }
}
