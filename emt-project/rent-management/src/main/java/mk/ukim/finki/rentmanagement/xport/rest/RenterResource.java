package mk.ukim.finki.rentmanagement.xport.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.rentmanagement.domain.model.*;
import mk.ukim.finki.rentmanagement.service.RenterService;
import mk.ukim.finki.rentmanagement.service.forms.RentItemForm;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/renter")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class RenterResource {

    private final RenterService renterService;

    // Ovoj metod go vrakja iznajmitelot so specificiranoto ID
    @GetMapping("/{id}")
    public Renter getRenter(@PathVariable String id) {
        return renterService.findById(RenterId.of(id));
    }

    // Ovoj metod sluzi za kreiranje na nov iznajmitel
    @PostMapping("/new")
    public Renter createRenter(@RequestBody Renter renter) {
        return renterService.createRenter(renter);
    }

    // Ovoj metod sluzi za editiranje na iznajmitel so specificiranoto ID
    @PostMapping("/{id}/edit")
    public Renter editRenter(@PathVariable String id,@RequestBody Renter renter) {
        return renterService.changeRenterData(RenterId.of(id), renter);
    }

    // Ovoj metod sluzi za dodavanje na nov RentItem
    @PostMapping("/{renterId}/rent/{rentId}/add-rent-item")
    public Rent addRentItem(@PathVariable String renterId, @PathVariable String rentId, @RequestBody RentItemForm rentItemForm) {
        return renterService.addRentItem(RenterId.of(renterId), RentId.of(rentId), rentItemForm);
    }

    // Ovoj metod sluzi za brisenje na postoecki RentItem
    @DeleteMapping("/{renterId}/rent/{rentId}/delete-rent-item/{rentItemId}")
    public Rent deleteRentItem(@PathVariable String renterId, @PathVariable String rentId, @PathVariable String rentItemId) {
        return renterService.removeRentItem(RenterId.of(renterId), RentId.of(rentId), RentItemId.of(rentItemId));
    }

    // Ovoj metod sluzi za brisenje na Renta
    @DeleteMapping("/{renterId}/rent/{rentId}/delete")
    public Rent removeRent(@PathVariable String renterId, @PathVariable String rentId) {
        return renterService.removeRent(RenterId.of(renterId), RentId.of(rentId));
    }

    // Ovoj metod sluzi za celosno kompletiranje na Renta
    @PostMapping("/{renterId}/rent/{rentId}/complete")
    public Rent completeRent(@PathVariable String renterId, @PathVariable String rentId) {
        return renterService.completeRent(RenterId.of(renterId), RentId.of(rentId));
    }

    // Ovoj metod sluzi za promena na vremenskiot interval vo koj trae rentata
    @PostMapping("/{renterId}/extend-reduce/{rentItemId}")
    public Rent extendReduceRent(@PathVariable String renterId, @PathVariable String rentItemId, @RequestBody LocalDateTime from, @RequestBody LocalDateTime to){
        return renterService.extendReduceRentItem(RenterId.of(renterId), RentItemId.of(rentItemId), from, to);
    }
}
