package java.application.core.domain.dto.person;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PersonDTO {
    String name;
    String region;
    String fishes;
    String phoneNumber;
}
