package com.application.resources;

import com.application.utils.core.resquests.DataRequest;
import com.application.utils.exeption.ApplicationBusinessException;
import com.application.core.commons.DomainReturnCode;
import com.application.core.domain.dto.person.PersonDTO;
import com.application.core.service.PersonService;
import com.application.utils.core.responses.DataListResponse;
import com.application.utils.core.responses.DataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/person")
public class PersonController {
    private static final String LOW = "Low";
    private static final String MODERATE = "Moderate";
    private static final String VERY_HIGH = "Very High";

    @Autowired
    PersonService personService;

    @GetMapping(
            value = ""
    )
    public DataListResponse<PersonDTO> list(
            HttpServletResponse servletResponse
    ) {

        DataListResponse<PersonDTO> response = new DataListResponse<>();

        try {
            response = personService.list();
            response.setMessage(DomainReturnCode.SUCCESSFUL_OPERATION.getDesc());
            servletResponse.setStatus(HttpServletResponse.SC_OK);
            return response;

        } catch (ApplicationBusinessException error) {
            response.setResponse(error);
            response.setSeverity(MODERATE);
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return response;
    }

    @GetMapping(
            value = "{id}"
    )
    public DataResponse<PersonDTO> get(
            @PathVariable(value = "id") Long id,
            HttpServletResponse servletResponse
    ) {
        System.out.println("get");
        DataResponse<PersonDTO> response = new DataResponse<>();

        try {
            response = personService.getById(id);
            response.setMessage(DomainReturnCode.SUCCESSFUL_OPERATION.getDesc());
            servletResponse.setStatus(HttpServletResponse.SC_OK);
            return response;

        } catch (ApplicationBusinessException error) {
            response.setResponse(error);
            response.setSeverity(LOW);
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return response;
    }

    @DeleteMapping(
            value = "{id}"
    )
    public DataResponse<PersonDTO> delete(
            @PathVariable(value = "id") Long id,
            HttpServletResponse servletResponse
    ) {
        System.out.println("delete");
        DataResponse<PersonDTO> response = new DataResponse<>();

        try {
            response = personService.delete(id);
            response.setMessage(DomainReturnCode.SUCCESSFUL_OPERATION.getDesc());
            servletResponse.setStatus(HttpServletResponse.SC_OK);
            return response;

        } catch (ApplicationBusinessException error) {
            response.setResponse(error);
            response.setSeverity(LOW);
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return response;
    }
    @PostMapping(
            value = "/create",
            consumes = "application/json",
            produces = "application/json"
    )
    public DataResponse<PersonDTO> create(
            @RequestBody PersonDTO bodyRequest,
            HttpServletResponse servletResponse
    ) {

        DataRequest<PersonDTO> request = new DataRequest<>(bodyRequest, "br");
        DataResponse<PersonDTO> response = new DataResponse<>();

        try {
            response = personService.create(request);
            response.setMessage(DomainReturnCode.SUCCESSFUL_OPERATION.getDesc());
            servletResponse.setStatus(HttpServletResponse.SC_OK);

            return response;

        } catch (ApplicationBusinessException error) {
            response.setResponse(error);
            response.setSeverity(VERY_HIGH);
            servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            return response;
        }
    }
}
