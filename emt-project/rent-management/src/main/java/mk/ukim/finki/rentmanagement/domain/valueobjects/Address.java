package mk.ukim.finki.rentmanagement.domain.valueobjects;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.base.ValueObject;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Address implements ValueObject {
    private final String street;
    private final Integer streetNumber;
    private final String city;
    private final String country;

    protected Address() {
        this.street = "";
        this.streetNumber = 0;
        this.city = "";
        this.country = "";
    }

    public Address(String street, Integer streetNumber, String city, String country) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
        this.country = country;
    }
}
