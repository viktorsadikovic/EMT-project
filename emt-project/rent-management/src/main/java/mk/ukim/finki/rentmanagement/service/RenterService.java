package mk.ukim.finki.rentmanagement.service;

import mk.ukim.finki.rentmanagement.domain.model.*;
import mk.ukim.finki.rentmanagement.service.forms.RentItemForm;

import java.time.Instant;
import java.time.LocalDateTime;

public interface RenterService {
    Renter findById(RenterId renterId);
    Renter createRenter(Renter renter);
    Renter changeRenterData(RenterId renterId, Renter renter);
    Rent addRentItem(RenterId renterId, RentId rentId, RentItemForm rentItemForm);
    Rent removeRentItem(RenterId renterId, RentId rentId, RentItemId rentItemId);
    Rent removeRent(RenterId renterId, RentId rentId);
    Rent completeRent(RenterId renterId, RentId rentId);
    Rent extendReduceRentItem(RenterId renterId, RentItemId rentItemId, LocalDateTime from, LocalDateTime to);
}
