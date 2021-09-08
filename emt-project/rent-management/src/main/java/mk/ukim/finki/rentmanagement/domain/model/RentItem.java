package mk.ukim.finki.rentmanagement.domain.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.rentmanagement.domain.valueobjects.VideoGameId;
import mk.ukim.finki.sharedkernel.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.base.DomainObjectId;
import mk.ukim.finki.sharedkernel.financial.Money;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "rent_item")
@Getter
public class RentItem extends AbstractEntity<RentItemId> {

    // Money e value object koj sodrzi valuta i suma na pari
    private Money itemPrice;

    private LocalDateTime dateFrom;

    private LocalDateTime dateTo;

    private int quantity;

    @AttributeOverride(name = "id", column = @Column(name = "videoGame_id", nullable = false))
    private VideoGameId videoGameId;

    private RentItem() {
        super(RentItemId.randomId(RentItemId.class));
    }

    public RentItem(@NonNull VideoGameId videoGameId, @NonNull Money itemPrice, int quantity, @NotNull LocalDateTime dateFrom, @NotNull LocalDateTime dateTo) {
        super(DomainObjectId.randomId(RentItemId.class));
        this.videoGameId = videoGameId;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Money subtotal() { return itemPrice.multiply(quantity); }

    public void changeRentTime(LocalDateTime dateFrom, LocalDateTime dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
}
