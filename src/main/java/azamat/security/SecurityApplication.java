package azamat.security;

import azamat.security.domain.Person;
import azamat.security.domain.Role;
import azamat.security.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    CommandLineRunner run (UserService userService){
        return args -> {
          userService.saveRole(new Role(null,"ROlE_USER"));
          userService.saveRole(new Role(null,"ROLE_ADMIN"));
          userService.saveRole(new Role(null,"ROLE_MANAGER"));
          userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));

          userService.saveUser(new Person(null,"Azamat","aza","1234",new ArrayList<>()));
          userService.saveUser(new Person(null,"Aibek","aiba","4321",new ArrayList<>()));
          userService.saveUser(new Person(null,"Esen","eseke","1357",new ArrayList<>()));
          userService.saveUser(new Person(null,"Kubantai","tamada","2468",new ArrayList<>()));
          userService.saveUser(new Person(null,"Cholponbek","chopo","8642",new ArrayList<>()));

          userService.addRoleToUser("aza","ROLE_ADMIN");
          userService.addRoleToUser("aiba","ROLE_SUPER_ADMIN");
          userService.addRoleToUser("eseke","ROLE_MANAGER");
          userService.addRoleToUser("aza","ROLE_USER");
          userService.addRoleToUser("tamada","ROLE_ADMIN");
          userService.addRoleToUser("chopo","ROLE_SUPER_ADMIN");

        };

    }

}
