package mk.ukim.finki.rentmanagement.domain.model;

import lombok.Getter;
import mk.ukim.finki.rentmanagement.domain.valueobjects.Address;
import mk.ukim.finki.rentmanagement.domain.valueobjects.RenterName;
import mk.ukim.finki.sharedkernel.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.financial.Money;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "renters")
@Getter
public class Renter extends AbstractEntity<RenterId> {

    // RenterName e Value Object koj vo sebe sodrzi ime(name:String) i prezime(surname:String)
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "renter_name")),
            @AttributeOverride(name = "surname", column = @Column(name = "renter_surname"))
    })
    private RenterName renterName;

    private String email;

    // Address e Value Object koj vo sebe sodrzi street, streetNumber, city i country
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "renter_street")),
            @AttributeOverride(name = "streetNumber", column = @Column(name = "renter_streetNumber")),
            @AttributeOverride(name = "city", column = @Column(name = "renter_city")),
            @AttributeOverride(name = "country", column = @Column(name = "renter_country"))
    })
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Rent> rentList;

    private Renter() {
        super(RenterId.randomId(RenterId.class));
        this.rentList = new HashSet<>();
    }

    public static Renter build(Renter renterForm) {
        Renter renter = new Renter();
        renter.renterName = renterForm.getRenterName();
        renter.email = renterForm.email;
        renter.address = renterForm.address;
        renter.rentList = renterForm.getRentList();

        return renter;
    }

    public void changeRenter(Renter renter) {
        this.renterName = renter.getRenterName();
        this.email = renter.getEmail();
        this.address = renter.getAddress();
    }

    public Rent addRent(Rent rent) {
        rentList.add(rent);

        return rent;
    }
}
