package com.hostbooks.crud.validator;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorResponse {
    public static List<Map<String,String>> getErrorResponse(Errors errors){
        List<Map<String,String>> list= new ArrayList<>();

        errors.getFieldErrors().forEach(o->{
            Map<String,String> map= new HashMap<>();
            map.put("Field", o.getField());
            map.put("Default Message",o.getDefaultMessage());
            map.put("Error Code", o.getCode());
            list.add(map);

        });
        return list;
    }
}
