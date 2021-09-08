package mk.ukim.finki.rentmanagement.domain.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.rentmanagement.domain.valueobjects.VideoGame;
import mk.ukim.finki.sharedkernel.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.financial.Currency;
import mk.ukim.finki.sharedkernel.financial.Money;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "rents")
@Getter
public class Rent extends AbstractEntity<RentId> {

    @Column(name = "order_currency")
    @Enumerated(EnumType.STRING)
    private Money total;

    @Enumerated(EnumType.STRING)
    private RentState rentState;

    private Instant rentedOn;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<RentItem> rentItemList;

    public Rent() {
        super(RentId.randomId(RentId.class));
        this.rentItemList = new HashSet<>();
        this.rentedOn = Instant.now();
        this.rentState = RentState.PENDING;
    }

    public Money total() {
        this.total = rentItemList.stream()
                .map(RentItem::getItemPrice)
                .reduce(new Money(Currency.USD, 0), Money::add);

        return this.total;
    }

    public RentItem addItem(RentItem rentItem) {
        rentItemList.add(rentItem);

        return rentItem;
    }

    public void removeItem(@NonNull RentItemId orderItemId) {
        Objects.requireNonNull(orderItemId, "Rent Item must not be null");
        rentItemList.removeIf(item -> item.getId().equals(orderItemId));
    }

    public void completeRent() {
        this.rentState = RentState.COMPLETED;
        this.rentedOn = Instant.now();
    }

    public void cancelRent() {
        this.rentState = RentState.CANCELLED;
    }
}
