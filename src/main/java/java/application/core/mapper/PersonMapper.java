package java.application.core.mapper;

import java.application.core.domain.dto.person.PersonDTO;
import java.application.core.domain.entity.person.Person;

public class PersonMapper {
    private PersonMapper() {
    }

    public static Person createEntityFromDTO(PersonDTO dto) {
        return Person.builder()
                .fishes(dto.getFishes())
                .name(dto.getName())
                .region(dto.getRegion())
                .phoneNumber(dto.getPhoneNumber())
                .build();
    }

    public static PersonDTO createDTOFromEntity(Person entity) {
        return PersonDTO.builder()
                .fishes(entity.getFishes())
                .name(entity.getName())
                .region(entity.getRegion())
                .phoneNumber(entity.getPhoneNumber())
                .build();
    }
}
