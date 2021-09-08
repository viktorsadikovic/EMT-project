package mk.ukim.finki.videogamescatalog.domain.repository;

import mk.ukim.finki.videogamescatalog.domain.models.VideoGame;
import mk.ukim.finki.videogamescatalog.domain.models.VideoGameId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoGameRepository extends JpaRepository<VideoGame, VideoGameId> {
}
