package com.hostbooks.crud.validator;

import com.hostbooks.crud.controller.EmpController;
import com.hostbooks.crud.models.EmployeeDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice(assignableTypes = EmpController.class)
public class EmployeeValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return EmployeeDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EmployeeDTO empDto=(EmployeeDTO)target;

        if(empDto.getImageUrl().isEmpty()){
            errors.rejectValue("imageUrl","404","imageUrl should not be null");
        }
        if(empDto.getMobile()==null || !empDto.getMobile().matches("[6-9][0-9]{9}")){
            errors.rejectValue("mobile","", "Mobile number must be valid");
        }
        if(empDto.getEmailId()==null || !empDto.getEmailId().matches("^(.+)@(\\S+)$")){
            errors.rejectValue("emailId","404", "please enter a valid email id");
        }
        if(empDto.getName().length()<3 || empDto.getName()==null){
            errors.rejectValue("Name", "404","Please enter a valid name");
        }
    }
}
