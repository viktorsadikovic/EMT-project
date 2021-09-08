package mk.ukim.finki.rentmanagement.service.forms;

import com.sun.istack.NotNull;
import lombok.Data;
import mk.ukim.finki.sharedkernel.financial.Currency;

import java.util.ArrayList;
import java.util.List;

// Ovaa klasa sluzi pri isprakjanje na podatoci od Front-end do Back-end pri kreirnajeto na Rent
@Data
public class RentForm {
    @NotNull
    private Currency currency;

    private List<RentItemForm> items = new ArrayList<>();
}
