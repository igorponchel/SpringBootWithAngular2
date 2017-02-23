package com.droidablebee.springboot.rest.endpoint;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.droidablebee.springboot.rest.domain.Technicien;

public class TechnicienValidator implements Validator { 

    @Override
    public boolean supports(Class<?> clazz) {
        return Technicien.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "middleName", "validation.message.field.required");
    }

}