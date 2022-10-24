package com.application.core.commons;

import com.application.utils.returns.IDomainReturnCode;
import com.application.utils.returns.InternationalizationUtil;
import org.springframework.context.MessageSource;

public enum DomainReturnCode implements IDomainReturnCode {

    /*
        Operations
     */
    SUCCESSFUL_OPERATION("Operation concluded with success"),
    ERROR_OPERATION("An error occurred during the operation"),

    /*
      Person
   */
    PERSON_NOT_FOUND("Person id not found in database"),
    PERSON_DATA_NOT_FOUND("Person data not found in database"),
    PERSON_NAME_IS_EMPTY("Person name is empty"),
    PERSON_FISHES_IS_EMPTY("Person fishes is empty"),
    PERSON_REGION_IS_EMPTY("Person region is empty"),
    PERSON_PHONE_IS_EMPTY("Person phone is empty"),
    PERSON_TYPE_IS_EMPTY("Person type is empty");

    private final String desc;

    DomainReturnCode(String value) {
        desc = value;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String getTranslatedDescription(MessageSource messageSource, String locale) {
        return InternationalizationUtil.getMessage(messageSource, getDesc(), locale);
    }

}
