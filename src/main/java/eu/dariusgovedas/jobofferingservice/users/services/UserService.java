package eu.dariusgovedas.jobofferingservice.users.services;

import eu.dariusgovedas.jobofferingservice.users.entities.User;
import eu.dariusgovedas.jobofferingservice.users.repositories.UserJPARepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserJPARepository userJPARepository;

    public Page<User> getFreelancers(Pageable pageable) {
        return userJPARepository.getFreelancers(pageable);
    }

    public Page<User> getRecruiters(Pageable pageable) {
        return userJPARepository.getRecruiters(pageable);
    }

    private List<User> getAppUsers() {
        return userJPARepository.findAll();
    }

    public User findUserByUsername(String username){
        return userJPARepository.findByUsernameEquals(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userJPARepository.findUserWithRoles(username)
                .orElseThrow();
    }

    public void createUser(User user) {
        userJPARepository.save(user);
    }
}
