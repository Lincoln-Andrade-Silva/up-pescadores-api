package core.application.core.mapper;

import core.application.core.domain.dto.person.PersonDTO;
import core.application.core.domain.entity.person.Person;

public class PersonMapper {
    private PersonMapper() {
    }

    public static Person createEntityFromDTO(PersonDTO dto) {
        Person person = new Person();
        person.setName(dto.getName());
        person.setFishes(dto.getFishes());
        person.setRegion(dto.getRegion());
        person.setPhoneNumber(dto.getPhoneNumber());
        return person;
    }

    public static PersonDTO createDTOFromEntity(Person entity) {
        PersonDTO person = new PersonDTO();
        person.setName(entity.getName());
        person.setFishes(entity.getFishes());
        person.setRegion(entity.getRegion());
        person.setPhoneNumber(entity.getPhoneNumber());
        return person;
    }
}
