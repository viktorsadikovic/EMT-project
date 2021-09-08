package mk.ukim.finki.rentmanagement.domain.model;

import lombok.NonNull;
import mk.ukim.finki.sharedkernel.base.DomainObjectId;

public class RentId extends DomainObjectId {

    private RentId() {
        super(RentId.randomId(RentId.class).getId());
    }

    public RentId(@NonNull String uuid) {
        super(uuid);
    }

    public static RentId of(@NonNull String uuid) {
        return new RentId(uuid);
    }

}
