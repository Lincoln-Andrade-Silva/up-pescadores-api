package java.application.core.domain.entity.person;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_person")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "region", nullable = false)
    String region;

    @Column(name = "fishes", nullable = false)
    String fishes;

    @Column(name = "phoneNumber", nullable = false)
    String phoneNumber;

}
