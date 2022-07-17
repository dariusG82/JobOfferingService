package eu.dariusgovedas.jobofferingservice.repositories;

import eu.dariusgovedas.jobofferingservice.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJPARepository extends JpaRepository<AppUser, Long> {
}
