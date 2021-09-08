package mk.ukim.finki.rentmanagement.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.rentmanagement.domain.model.*;
import mk.ukim.finki.rentmanagement.domain.repository.RenterRepository;
import mk.ukim.finki.rentmanagement.service.RenterService;
import mk.ukim.finki.rentmanagement.service.forms.RentItemForm;
import mk.ukim.finki.sharedkernel.events.rents.RentItemCreated;
import mk.ukim.finki.sharedkernel.events.rents.RentItemRemoved;
import mk.ukim.finki.sharedkernel.infra.DomainEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

// Vo ramkite na ovoj ogranicen kontekst, Aggregate Root e Renter sto znaci deka site operacii koi sto se izvrsuvaat
// vrz Renter, Rent i RentItem entitetite se realiziraat preku Renter
@Service
@AllArgsConstructor
public class RenterServiceImpl implements RenterService {

    private final RenterRepository renterRepository;
    private final DomainEventPublisher domainEventPublisher;

    // Ovoj metod sluzi za vrakjanje na renterot so specifiranoto ID od baza
    @Override
    public Renter findById(RenterId renterId) {
        return renterRepository.findById(renterId).orElseThrow(RuntimeException::new);
    }

    // Ovoj metod sluzi za kreiranje na nov Renter vo baza
    @Override
    public Renter createRenter(Renter renter) {
        Renter newRenter = Renter.build(renter);

        return renterRepository.save(newRenter);
    }

    // Ovoj metod sluzi za promena na podatocite na daden Renter
    @Override
    public Renter changeRenterData(RenterId renterId, Renter renter) {
        Renter existingRenter = findById(renterId);

        existingRenter.changeRenter(renter);

        return renterRepository.saveAndFlush(existingRenter);
    }

    // Ovoj metod sluzi za kreiranje na nov RentItem i voedno pri kreiranjeto na RentItem se ispaluva event koj potrebno
    // e da bide faten vo VideoGamesCatalog ograniceniot kontekst
    @Override
    public Rent addRentItem(RenterId renterId, RentId rentId, RentItemForm rentItemForm) {
        // Go naogjame renterot dokolku postoi
        Renter renter = findById(renterId);
        // Potoa kreirame RentItem
        RentItem rentItem = new RentItem(rentItemForm.getVideoGame().getId(),
                rentItemForm.getVideoGame().getPrice(),
                rentItemForm.getQuantity(),
                rentItemForm.getDateFrom(),
                rentItemForm.getDateTo());

        Rent existingRent;
        // Dokolku renterot veke ima kreirano Rent, se zema toj objekt, a dokolku nema, se kreira nov Rent objekt
        if (renter.getRentList().stream().noneMatch(rent -> rent.getId().equals(rentId))) {
            existingRent = new Rent();
            renter.addRent(existingRent);
        } else {
            existingRent = renter.getRentList().stream()
                    .filter(rent -> rent.getId().equals(rentId))
                    .findFirst()
                    .orElseThrow(RuntimeException::new);
        }

        // Vo ramki na Rent objektot se dodava noviot RentItem i se pravi rekalkulacija na sumata
        existingRent.addItem(rentItem);
        existingRent.total();

        renterRepository.saveAndFlush(renter);

        // Ispaluvanje na event koj kazuva deka e dodaden nov RentItem za dadena igra i potrebno e da se namali brojot na
        // dostapni primeroci za dadenata igra od strana na VideoGamesCatalog ograniceniot kontekst
        domainEventPublisher.publish(new RentItemCreated(rentItem.getVideoGameId().getId(),
                rentItem.getQuantity()));

        return existingRent;
    }

    // Ovoj metod sluzi za brisenje na postoecki RentItem i voedno pri brisenjeto na RentItem-ot se ispaluva event koj potrebno
    // e da bide faten vo VideoGamesCatalog ograniceniot kontekst
    @Override
    public Rent removeRentItem(RenterId renterId, RentId rentId, RentItemId rentItemId) {
        // Go naogjame renterot dokolku postoi
        Renter renter = findById(renterId);

        // Go naogjame Rent objektot koj go barame vo ramki na listata na Rents kaj Renter-ot
        Rent existingRent = renter.getRentList().stream()
                .filter(rent -> rent.getId().getId().equals(rentId.getId()))
                .findFirst()
                .orElseThrow(RuntimeException::new);

        // Go naogjame RentItem objektot koj go barame vo ramkite na listate od RentItems vo Rent-ot, so cel da go izbriseme posakuvaniot RentItem
        RentItem existingRentItem = existingRent.getRentItemList()
                .stream()
                .filter(rentItem -> rentItem.getId().getId().equals(rentItemId.getId()))
                .findFirst()
                .orElseThrow(RuntimeException::new);

        // Po brisenjeto na RentItem-ot, povtorno pravime rekalkulacija na sumata na celiot Rent
        existingRent.total();

        renterRepository.saveAndFlush(renter);

        // Ispaluvanje na event koj kazuva deka e izbrisan RentItem za dadena igra i potrebno e da se zgolemi brojot na
        // dostapni primeroci za dadenata igra od strana na VideoGamesCatalog ograniceniot kontekst
        domainEventPublisher.publish(new RentItemRemoved(existingRentItem.getId().getId(),
                existingRentItem.getQuantity()));

        return existingRent;
    }

    // Ovoj metod sluzi za brisenje na Rent, brisenjeto go pravime na toj nacin sto prvo go naogjame Renterot, a potoa
    // od negovata lista na Rents go briseme posakuvaniot Rent
    @Override
    public Rent removeRent(RenterId renterId, RentId rentId) {
        Renter renter = findById(renterId);
        renter.getRentList()
                .removeIf(r -> r.getId().getId().equals(rentId.getId()));

        renterRepository.saveAndFlush(renter);

        return null;
    }

    // Ovoj metod sluzi za kompletiranje na Rent, kompletiranjeto go pravime na toj nacin sto prvo go naogjame Renterot, a potoa
    // od negovata lista na Rents go naogjame posakuvaniot i pravime promena na statusot na Rent-ot
    @Override
    public Rent completeRent(RenterId renterId, RentId rentId) {
        Renter renter = findById(renterId);
        Rent rent = renter.getRentList().stream()
                .filter(r -> r.getId().getId().equals(rentId.getId()))
                .findFirst()
                .orElseThrow(RuntimeException::new);

        rent.completeRent();
        rent.total();

        renterRepository.saveAndFlush(renter);

        return rent;
    }

    // Ovoj metod sluzi za promena na vremenskiot interval na Rent-ot, istoto go pravime taka sto prvo go naogjame Renterot,
    // potoa vo listata na Rents ja naogjame posakuvanata Renta i ja pravime izmenata
    @Override
    public Rent extendReduceRentItem(RenterId renterId, RentItemId rentItemId, LocalDateTime from, LocalDateTime to) {
        Renter renter = findById(renterId);

        RentItem existingRentItem = renter.getRentList().stream()
                .flatMap(rent -> rent.getRentItemList().stream())
                .filter(rentItem -> rentItem.getId().equals(rentItemId))
                .findFirst()
                .orElseThrow(RuntimeException::new);

        existingRentItem.changeRentTime(from, to);

        renterRepository.saveAndFlush(renter);

        return null;
    }
}
