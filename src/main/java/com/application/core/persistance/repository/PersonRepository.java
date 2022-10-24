package com.application.core.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.core.domain.entity.person.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
