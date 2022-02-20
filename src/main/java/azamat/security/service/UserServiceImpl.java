package azamat.security.service;
import azamat.security.domain.Person;
import azamat.security.domain.Role;
import azamat.security.repository.RoleRepo;
import azamat.security.repository.PersonRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j

public class UserServiceImpl implements UserService, UserDetailsService {
    private final PersonRepo personRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Person user = personRepo.findByUsername(username);
        if(user==null){
            log.error("User not found");
            throw new UsernameNotFoundException("User not found");
        }else {
            log.info("User found: {}",username);}
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {authorities.add(new SimpleGrantedAuthority(role.getName()));
            });

        return new User(user.getUsername(),user.getPassword(),authorities);
    }
    @Override
    public Person saveUser(Person person) {
        log.info("Saving new user {} to the database", person.getName());
        person.setPassword(passwordEncoder.encode(person.getPassword()));
         return personRepo.save(person);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database",role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user{}",roleName,username);
        Person person = personRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        person.getRoles().add(role);
    }

    @Override
    public Person getUser(String username) {
        log.info("Fetching user {}",username);
        return personRepo.findByUsername(username);
    }

    @Override
    public List<Person> getUsers() {
        log.info("Fetching all users");
        return personRepo.findAll();
    }


}
