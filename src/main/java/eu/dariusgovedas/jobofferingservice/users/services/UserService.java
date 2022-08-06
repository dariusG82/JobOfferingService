package eu.dariusgovedas.jobofferingservice.users.services;

import eu.dariusgovedas.jobofferingservice.users.entities.*;
import eu.dariusgovedas.jobofferingservice.users.repositories.UserJPARepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    public User registerNewUser(UserDTO userDTO) {
        User user = new User();
        user.setName(getFormattedNameOrSurname(userDTO.getName()));
        user.setSurname(getFormattedNameOrSurname(userDTO.getSurname()));
        user.setUsername(userDTO.getUsername());
        user.setPassword("{bcrypt}"+passwordEncoder.encode(userDTO.getPassword()));
        user.setEmailAddress(userDTO.getEmailAddress().toLowerCase());
        user.setPhoneNumber(getInternationalNumber(userDTO.getPhoneNumber()));
        if(userIsRecruiter(userDTO)){
            return createRecruiter(user, userDTO);
        } else {
            return createFreelancer(user);
        }
    }

    private String getFormattedNameOrSurname(String userData){
        return userData.substring(0,1).toUpperCase() + userData.substring(1).toLowerCase();
    }

    private String getInternationalNumber(String phoneNumber) {
        return phoneNumber.startsWith("86") ? "+3706" + phoneNumber.substring(2) : phoneNumber;
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

    public boolean emailExists(String email){
        return userJPARepository.findByEmail(email.toLowerCase()) != null;
    }

    public boolean usernameExists(String username){
        return userJPARepository.findByUsername(username) != null;
    }
}
