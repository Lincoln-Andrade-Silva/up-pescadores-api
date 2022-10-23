package core.application.core.domain.entity.person;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getFishes() {
        return fishes;
    }

    public void setFishes(String fishes) {
        this.fishes = fishes;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
