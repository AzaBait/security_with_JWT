package azamat.security.repository;

import azamat.security.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo extends JpaRepository<Person,Long> {
    Person findByUsername(String username);
}
