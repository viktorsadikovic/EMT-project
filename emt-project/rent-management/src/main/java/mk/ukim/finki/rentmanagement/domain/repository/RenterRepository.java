package mk.ukim.finki.rentmanagement.domain.repository;

import mk.ukim.finki.rentmanagement.domain.model.Renter;
import mk.ukim.finki.rentmanagement.domain.model.RenterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RenterRepository extends JpaRepository<Renter, RenterId> {
}
