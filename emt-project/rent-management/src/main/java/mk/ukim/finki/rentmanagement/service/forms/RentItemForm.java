package mk.ukim.finki.rentmanagement.service.forms;

import lombok.Data;
import mk.ukim.finki.rentmanagement.domain.valueobjects.VideoGame;

import java.time.Instant;
import java.time.LocalDateTime;

// Ovaa klasa sluzi pri isprakjanje na podatoci od Front-end do Back-end pri kreirnajeto na RentItem
@Data
public class RentItemForm {

    private VideoGame videoGame;

    private int quantity;

    private LocalDateTime dateFrom;

    private LocalDateTime dateTo;
}
