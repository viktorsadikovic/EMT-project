package mk.ukim.finki.rentmanagement.domain.valueobjects;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.base.ValueObject;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class RenterName implements ValueObject {
    private final String name;
    private final String surname;

    protected RenterName() {
        this.name = "";
        this.surname = "";
    }

    public RenterName(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
