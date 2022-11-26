package com.application.core.domain.dto.person;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class PersonDTO {
    Long id;
    String name;
    String region;
    String fishes;
    String phoneNumber;

    public PersonDTO(){}
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
