package mk.ukim.finki.videogamescatalog.xport.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import mk.ukim.finki.sharedkernel.config.TopicHolder;
import mk.ukim.finki.sharedkernel.events.DomainEvent;
import mk.ukim.finki.sharedkernel.events.rents.RentItemCreated;
import mk.ukim.finki.videogamescatalog.domain.models.VideoGameId;
import mk.ukim.finki.videogamescatalog.service.VideoGameService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VideoGameEventListener {
    private final VideoGameService videoGameService;

    // Dokolku se kreira nov RentItem za dadena VideoGame, se ispaluva event koj kazuva deka e kreiran nov Rent Item so
    // cel da moze soodvetno da se namali brojot na dostapni kopii od izbranata igra
    @KafkaListener(topics = TopicHolder.TOPIC_RENT_ITEM_CREATED, groupId = "videoGameCatalog")
    public void consumeRentItemCreated(String jsonMessage) {
        try {

            RentItemCreated event = DomainEvent.fromJson(jsonMessage, RentItemCreated.class);
            videoGameService.rentItemCreated(VideoGameId.of(event.getProductId()), event.getQuantity());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    // Dokolku se izbrise veke kreiran RentItem za dadena VideoGame, se ispaluva event koj kazuva deka e izbrisan RentItem-ot so
    // cel da moze soodvetno da se zgolemi brojot na dostapni kopii od izbranata igra
    @KafkaListener(topics = TopicHolder.TOPIC_RENT_ITEM_REMOVED, groupId = "videoGameCatalog")
    public void consumeRentItemRemoved(String jsonMessage) {
        try {

            RentItemCreated event = DomainEvent.fromJson(jsonMessage, RentItemCreated.class);
            videoGameService.rentItemRemoved(VideoGameId.of(event.getProductId()), event.getQuantity());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
