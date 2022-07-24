package eu.dariusgovedas.jobofferingservice.users.services;

import eu.dariusgovedas.jobofferingservice.users.entities.User;
import eu.dariusgovedas.jobofferingservice.users.repositories.UserJPARepository;
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

    public Page<User> getFreelancers(Pageable pageable) {
        List<User> allUsers = getAppUsers();
        List<User> freelancers = getFreelancers(allUsers);

        return new PageImpl<>(freelancers, pageable, freelancers.size());
    }

    public Page<User> getRecruiters(Pageable pageable) {
        List<User> allUsers = getAppUsers();
        List<User> recruiters = getRecruiters(allUsers);

        return new PageImpl<>(recruiters, pageable, recruiters.size());
    }

    private List<User> getAppUsers() {
        return userJPARepository.findAll();
    }

    private List<User> getFreelancers(List<User> allUsers) {
        return allUsers.stream()
                .filter(appUser -> appUser.getFreelancer() != null)
                .toList();
    }

    private List<User> getRecruiters(List<User> allUsers) {
        return allUsers.stream()
                .filter(appUser -> appUser.getRecruiter() != null)
                .toList();
    }

}
