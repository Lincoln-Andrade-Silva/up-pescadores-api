package java.application.core.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.application.core.domain.entity.person.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
