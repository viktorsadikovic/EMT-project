package mk.ukim.finki.rentmanagement.domain.model;

import lombok.NonNull;
import mk.ukim.finki.sharedkernel.base.DomainObjectId;

public class RenterId extends DomainObjectId {

    private RenterId() {
        super(RenterId.randomId(RenterId.class).getId());
    }

    public RenterId(@NonNull String uuid) {
        super(uuid);
    }

    public static RenterId of(@NonNull String uuid) {
        return new RenterId(uuid);
    }
}
