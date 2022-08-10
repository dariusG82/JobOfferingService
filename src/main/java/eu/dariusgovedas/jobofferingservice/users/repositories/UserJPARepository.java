package eu.dariusgovedas.jobofferingservice.users.repositories;

import eu.dariusgovedas.jobofferingservice.users.entities.User;
import eu.dariusgovedas.jobofferingservice.users.enums.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJPARepository extends JpaRepository<User, String> {

    @Query("SELECT u FROM User u JOIN FETCH u.role WHERE u.username = :username AND u.status=:status")
    Optional<User> findUserWithRoles(@Param("username") String username, @Param("status") UserStatus status);

    @Query("FROM User u WHERE u.recruiter IS NOT NULL AND u.status =:status")
    Page<User> getRecruiters(@Param("status") UserStatus status, Pageable pageable);

    @Query("FROM User u WHERE u.freelancer IS NOT NULL AND u.status= :status")
    Page<User> getFreelancers(@Param("status") UserStatus status, Pageable pageable);

    User findByUsernameEquals(String username);

    @Query("FROM User u WHERE u.contactDetails.emailAddress = :email")
    User findByEmail(@Param("email") String email);

    @Query("FROM User u WHERE u.username = :username")
    User findByUsername(@Param("username") String username);
}
