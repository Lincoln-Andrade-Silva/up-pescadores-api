package com.application.core.service;

import com.application.utils.core.resquests.DataRequest;
import com.application.utils.exeption.ApplicationBusinessException;
import com.application.core.commons.DomainReturnCode;
import com.application.core.domain.dto.person.PersonDTO;
import com.application.core.domain.entity.person.Person;
import com.application.core.domain.validator.PersonValidator;
import com.application.core.mapper.PersonMapper;
import com.application.core.persistance.repository.PersonRepository;
import com.application.utils.core.responses.DataListResponse;
import com.application.utils.core.responses.DataResponse;
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

    public DataResponse<PersonDTO> delete(Long id) throws ApplicationBusinessException {
        DataResponse<PersonDTO> dataResponse = new DataResponse<>();

        Optional<Person> optionalPerson = personRepository.findById(id);
        Person person = PersonValidator.validateOptional(optionalPerson, messageSource, "pt-br");

        PersonDTO dto = PersonMapper.createDTOFromEntity(person);
        personRepository.delete(person);

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

    public DataResponse<PersonDTO> edit(Long id, PersonDTO request) throws ApplicationBusinessException {
        DataResponse<PersonDTO> dataResponse = new DataResponse<>();

        Optional<Person> optionalPerson = personRepository.findById(id);
        Person person = PersonValidator.validateOptional(optionalPerson, messageSource, "pt-br");

        PersonMapper.editPerson(person, request);
        personRepository.save(person);

        dataResponse.setData(request);
        dataResponse.setMessage(DomainReturnCode.SUCCESSFUL_OPERATION.getDesc());
        return dataResponse;
    }
}
