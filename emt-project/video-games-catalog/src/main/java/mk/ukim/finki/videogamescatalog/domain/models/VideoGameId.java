package mk.ukim.finki.videogamescatalog.domain.models;

import lombok.NonNull;
import mk.ukim.finki.sharedkernel.base.DomainObjectId;

public class VideoGameId extends DomainObjectId {

    private VideoGameId() {
        super(VideoGameId.randomId(VideoGameId.class).getId());
    }

    public VideoGameId(@NonNull String uuid) {
        super(uuid);
    }

    public static VideoGameId of(@NonNull String uuid) {
        return new VideoGameId(uuid);
    }
}
