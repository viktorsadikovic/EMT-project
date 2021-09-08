package mk.ukim.finki.rentmanagement.domain.model;

import lombok.NonNull;
import mk.ukim.finki.sharedkernel.base.DomainObjectId;

public class RentItemId extends DomainObjectId {

    private RentItemId() {
        super(RentItemId.randomId(RentItemId.class).getId());
    }

    public RentItemId(@NonNull String uuid) {
        super(uuid);
    }
    public static RentItemId of(@NonNull String uuid) {
        return new RentItemId(uuid);
    }

}
