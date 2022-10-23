package java.application.core.service;

import java.application.core.commons.DomainReturnCode;
import java.application.core.domain.dto.person.PersonDTO;
import java.application.core.domain.entity.person.Person;
import java.application.core.domain.validator.PersonValidator;
import java.application.core.mapper.PersonMapper;
import java.application.core.persistance.repository.PersonRepository;
import java.application.utils.core.responses.DataListResponse;
import java.application.utils.core.responses.DataResponse;
import java.application.utils.core.resquests.DataRequest;
import java.application.utils.exeption.ApplicationBusinessException;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final MessageSource messageSource;

    public PersonService(PersonRepository personRepository, MessageSource messageSource) {
        this.personRepository = personRepository;
        this.messageSource = messageSource;
    }

    public DataListResponse<PersonDTO> list() throws ApplicationBusinessException {
        DataListResponse<PersonDTO> dataResponses = new DataListResponse<>();
        List<PersonDTO> personDTOS = new ArrayList<>();

        List<Person> personList = personRepository.findAll();
        PersonValidator.validateToList(personList, messageSource, "br");

        for (Person person : personList) {
            PersonDTO dto = PersonMapper.createDTOFromEntity(person);
            personDTOS.add(dto);
        }

        dataResponses.setData(personDTOS);
        return dataResponses;
    }

    public DataResponse<PersonDTO> getById(Long id) throws ApplicationBusinessException {
        DataResponse<PersonDTO> dataResponse = new DataResponse<>();

        Optional<Person> optionalPerson = personRepository.findById(id);
        Person person = PersonValidator.validateOptional(optionalPerson, messageSource, "pt-br");

        PersonDTO dto = PersonMapper.createDTOFromEntity(person);

        dataResponse.setData(dto);
        dataResponse.setMessage(DomainReturnCode.SUCCESSFUL_OPERATION.getDesc());
        return dataResponse;
    }

    public DataResponse<PersonDTO> create(DataRequest<PersonDTO> request) throws ApplicationBusinessException {
        DataResponse<PersonDTO> dataResponse = new DataResponse<>();

        Person person = PersonMapper.createEntityFromDTO(request.getData());
        PersonValidator.validateToCreate(person, messageSource, "br");

        personRepository.save(person);

        PersonDTO dto = PersonMapper.createDTOFromEntity(person);

        dataResponse.setData(dto);
        dataResponse.setMessage(DomainReturnCode.SUCCESSFUL_OPERATION.getDesc());
        return dataResponse;
    }
}
