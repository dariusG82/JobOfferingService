package eu.dariusgovedas.jobofferingservice.services;

import eu.dariusgovedas.jobofferingservice.entities.AppUser;
import eu.dariusgovedas.jobofferingservice.repositories.UserJPARepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserJPARepository userJPARepository;

    public Page<AppUser> getFreelancers(Pageable pageable) {
        List<AppUser> allUsers = userJPARepository.findAll();
        List<AppUser> freelancers = allUsers.stream()
                .filter(appUser -> appUser.getFreelancer() != null)
                .toList();

        return new PageImpl<>(freelancers, pageable, freelancers.size());
    }

}
