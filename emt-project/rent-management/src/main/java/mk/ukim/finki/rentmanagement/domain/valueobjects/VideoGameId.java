package mk.ukim.finki.rentmanagement.domain.valueobjects;

import mk.ukim.finki.sharedkernel.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class VideoGameId extends DomainObjectId {

    private VideoGameId() {
        super(VideoGameId.randomId(VideoGameId.class).getId());
    }

    public VideoGameId(String uuid) {
        super(uuid);
    }
}
