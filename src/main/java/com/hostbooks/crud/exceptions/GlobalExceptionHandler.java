package com.hostbooks.crud.exceptions;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<CustomError> employeeExceptionHandler(EmployeeException ee, WebRequest wrq){

        CustomError empError = new CustomError();
        empError.setLdt(LocalDateTime.now());
        empError.setMessage(ee.getMessage());
        empError.setDetails(wrq.getDescription(false));
        return new ResponseEntity<>(empError, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(AddressException.class)
    public ResponseEntity<CustomError> addressExceptionHandler(AddressException ae, WebRequest wrq){

        CustomError addError = new CustomError();
        addError.setLdt(LocalDateTime.now());
        addError.setMessage(ae.getMessage());
        addError.setDetails(wrq.getDescription(false));
        return new ResponseEntity<>(addError, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(BankingException.class)
    public ResponseEntity<CustomError> bankingExceptionHandler(BankingException be, WebRequest wrq){

        CustomError bankError = new CustomError();
        bankError.setLdt(LocalDateTime.now());
        bankError.setMessage(be.getMessage());
        bankError.setDetails(wrq.getDescription(false));
        return new ResponseEntity<>(bankError, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<CustomError> noSuchElementExceptionHandler(NoSuchElementException e, WebRequest wr){
        CustomError err = new CustomError(LocalDateTime.now(), e.getMessage(), wr.getDescription(false));
        return new ResponseEntity<CustomError>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<CustomError> noResultFoundExceptionHandler(EmptyResultDataAccessException e, WebRequest wr){
        CustomError err = new CustomError(LocalDateTime.now(), e.getMessage(), wr.getDescription(false));
        return new ResponseEntity<CustomError>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> validatorHandler(MethodArgumentNotValidException ie) {
        CustomError validatorError = new CustomError();
        validatorError.setLdt(LocalDateTime.now());
        validatorError.setMessage("Please enter the valid input");
        validatorError.setDetails(ie.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(validatorError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomError> otherExceptionHandler(Exception e, WebRequest req){
        CustomError otherErr=new CustomError();
        otherErr.setLdt(LocalDateTime.now());
        otherErr.setMessage(e.getMessage());
        otherErr.setDetails(req.getDescription(false));
        return new ResponseEntity<>(otherErr,HttpStatus.NOT_FOUND);
    }
}
