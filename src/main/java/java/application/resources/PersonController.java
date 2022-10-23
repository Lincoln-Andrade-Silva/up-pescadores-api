package java.application.resources;
import java.application.core.commons.DomainReturnCode;
import java.application.core.domain.dto.person.PersonDTO;
import java.application.core.service.PersonService;
import java.application.utils.core.responses.DataListResponse;
import java.application.utils.core.responses.DataResponse;
import java.application.utils.core.resquests.DataRequest;
import java.application.utils.exeption.ApplicationBusinessException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
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
    public ResponseEntity<List<PersonDTO>> list(
            HttpServletResponse servletResponse
    ) {

        DataListResponse<PersonDTO> response = new DataListResponse<>();

        try {
            response = personService.list();
            response.setMessage(DomainReturnCode.SUCCESSFUL_OPERATION.getDesc());
            servletResponse.setStatus(HttpServletResponse.SC_OK);
            return ResponseEntity.ok().body(response.getData());

        } catch (ApplicationBusinessException error) {
            response.setResponse(error);
            response.setSeverity(MODERATE);
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return ResponseEntity.ok().body(response.getData());
    }

    @GetMapping(
            value = "{id}"
    )
    public ResponseEntity<PersonDTO> get(
            @PathVariable(value = "id") Long id,
            HttpServletResponse servletResponse
    ) {

        DataResponse<PersonDTO> response = new DataResponse<>();

        try {
            response = personService.getById(id);
            response.setMessage(DomainReturnCode.SUCCESSFUL_OPERATION.getDesc());
            servletResponse.setStatus(HttpServletResponse.SC_OK);
            return ResponseEntity.ok().body(response.getData());

        } catch (ApplicationBusinessException error) {
            response.setResponse(error);
            response.setSeverity(LOW);
            servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return ResponseEntity.ok().body(response.getData());
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
