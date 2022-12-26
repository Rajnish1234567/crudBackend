package com.hostbooks.crud.validator;

import com.hostbooks.crud.models.AddressDTO;
import org.apache.tomcat.jni.Address;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice(assignableTypes = Address.class)
public class AddressValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return AddressDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AddressDTO aDto=(AddressDTO) target;

        if(aDto.getPincode()==null || !aDto.getPincode().matches("[1-9][0-9]{5}")){
            errors.rejectValue("pincode","404","please enter a valide pincode");
        }
        if(aDto.getState()==null ){
            errors.rejectValue("state","404", "please enter a valid state name");
        }
    }
}
