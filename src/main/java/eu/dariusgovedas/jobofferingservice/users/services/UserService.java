package eu.dariusgovedas.jobofferingservice.users.services;

import eu.dariusgovedas.jobofferingservice.users.entities.*;
import eu.dariusgovedas.jobofferingservice.users.repositories.UserJPARepository;
import eu.dariusgovedas.jobofferingservice.users.validation.UserAlreadyExistException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private PasswordEncoder passwordEncoder;

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

    @Transactional
    public void createUser(User user) {
        userJPARepository.save(user);
    }

    public User registerNewUser(UserDTO userDTO) throws UserAlreadyExistException {
        if(emailExists(userDTO.getEmailAddress())){
            throw new UserAlreadyExistException("There is an account with email " + userDTO.getEmailAddress());
        }
        if(usernameExists(userDTO.getUsername())){
            throw new UserAlreadyExistException("There is an account with username " + userDTO.getUsername());
        }

        User user = new User();
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setUsername(userDTO.getUsername());
        user.setPassword("{bcrypt}"+passwordEncoder.encode(userDTO.getPassword()));
        user.setEmailAddress(userDTO.getEmailAddress());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        if(userIsRecruiter(userDTO)){
            return createRecruiter(user, userDTO);
        } else {
            return createFreelancer(user);
        }
    }


    private User createRecruiter(User user, UserDTO userDTO){
        Recruiter recruiter = new Recruiter();
        recruiter.setBusinessName(userDTO.getBusinessName());
        user.setRecruiter(recruiter);
        Role role = new Role();
        role.setName("RECRUITER");
        user.setRole(role);
        createUser(user);
        return user;
    }

    private User createFreelancer(User user){
        Freelancer freelancer = new Freelancer();
        freelancer.setRating(BigDecimal.ZERO);
        freelancer.setJobsFinished(0);
        freelancer.setTotalIncome(BigDecimal.ZERO);
        user.setFreelancer(freelancer);
        Role role = new Role();
        role.setName("FREELANCER");
        user.setRole(role);
        createUser(user);
        return user;
    }

    private boolean userIsRecruiter(UserDTO userDTO) {
        return userDTO.getBusinessName() != null && !userDTO.getBusinessName().trim().isEmpty();

    }

    private boolean emailExists(String email){
        return userJPARepository.findByEmail(email) != null;
    }

    private boolean usernameExists(String username){
        return userJPARepository.findByUsername(username) != null;
    }
}
