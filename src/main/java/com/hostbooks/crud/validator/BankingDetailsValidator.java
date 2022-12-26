package com.hostbooks.crud.validator;

import com.hostbooks.crud.models.BankingDetails;
import com.hostbooks.crud.models.BankingDetailsDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice(assignableTypes = BankingDetails.class)
public class BankingDetailsValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return BankingDetailsDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BankingDetailsDTO bDto=(BankingDetailsDTO) target;

        if(bDto.getAadharNo().length()!=12){
            errors.rejectValue("aadharNo","404","Aadhar number is invalid");
        }
        if(bDto.getAccountNo()==null){
            errors.rejectValue("accountNo","404","Account number is mandatory");
        }
        if(bDto.getPanCardNo()==null){
            errors.rejectValue("pancardNo","404","please enter pancard details");
        }
        
    }
}
