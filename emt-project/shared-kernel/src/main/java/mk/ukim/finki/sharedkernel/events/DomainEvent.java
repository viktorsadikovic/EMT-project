package mk.ukim.finki.sharedkernel.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.time.Instant;

@Getter
public abstract class DomainEvent {
    private String topic;
    private Instant occuredOn;

    public DomainEvent(String topic) {
        this.occuredOn = Instant.now();
        this.topic = topic;
    }

    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        String output = null;
        try {
            output = objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return output;
    }

    public String topic() {
        return topic;
    }

    public static <E extends DomainEvent> E fromJson(String json, Class<E> eventClass) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(json, eventClass);
    }
}

