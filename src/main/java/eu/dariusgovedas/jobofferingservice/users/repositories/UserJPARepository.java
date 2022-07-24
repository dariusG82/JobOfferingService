package eu.dariusgovedas.jobofferingservice.users.repositories;

import eu.dariusgovedas.jobofferingservice.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJPARepository extends JpaRepository<User, Long> {
}
