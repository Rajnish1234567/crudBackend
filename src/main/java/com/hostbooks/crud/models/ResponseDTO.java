package com.hostbooks.crud.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private LocalDateTime time;
    private Object data;
    private HttpStatus msg;
    private Integer statusCode;
    private Object err;
    public static ResponseDTO getResponse(Object obj, LocalDateTime time, HttpStatus message, Integer status,Object errors ){
        ResponseDTO rDTO= new ResponseDTO();
        rDTO.setTime(time);
        rDTO.setData(obj);
        rDTO.setMsg(message);
        rDTO.setStatusCode(status);
        rDTO.setErr(errors);
        return rDTO;
    }

}
