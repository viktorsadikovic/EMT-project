package mk.ukim.finki.rentmanagement.infra;

import lombok.AllArgsConstructor;
import mk.ukim.finki.sharedkernel.events.DomainEvent;
import mk.ukim.finki.sharedkernel.infra.DomainEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

// Konfiguracija za EventPublisher-ot
@Service
@AllArgsConstructor
public class DomainEventPublisherImpl implements DomainEventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void publish(DomainEvent event) {
        this.kafkaTemplate.send(event.topic(), event.toJson());
    }
}
