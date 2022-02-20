package azamat.security.service;

import azamat.security.domain.Person;
import azamat.security.domain.Role;

import java.util.List;

public interface UserService {
    Person saveUser(Person person);
    Role saveRole(Role role);
    void addRoleToUser(String username,String roleName);
    Person getUser(String username);
    List<Person>getUsers();
}
