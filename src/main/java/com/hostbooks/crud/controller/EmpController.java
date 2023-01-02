package com.hostbooks.crud.controller;

import com.hostbooks.crud.models.EmployeeDTO;
import com.hostbooks.crud.models.PaginationDTO;
import com.hostbooks.crud.models.ResponseDTO;
import com.hostbooks.crud.services.EmpService;
import com.hostbooks.crud.validator.EmployeeValidator;
import com.hostbooks.crud.validator.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/hostbooks/emp")
@CrossOrigin(origins="*")
public class EmpController {
    @Autowired
    public EmpService empService;

    @Autowired
    EmployeeValidator empValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(empValidator);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveEmp(@Valid @RequestBody EmployeeDTO employeeDTO, Errors result){
        if(result.hasErrors()){
            return new ResponseEntity<>(ResponseDTO.getResponse(
                    null, LocalDateTime.now(),HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value(), ErrorResponse.getErrorResponse(result)), HttpStatus.OK);
        }
        EmployeeDTO addedEmp=empService.addEmployee(employeeDTO);
        return new ResponseEntity<>(ResponseDTO.getResponse(addedEmp,LocalDateTime.now()
                ,HttpStatus.OK,HttpStatus.OK.value(),null),HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> upadateEmp(@Valid @RequestBody EmployeeDTO empDto, Errors result) {
        if(result.hasErrors()){
            return new ResponseEntity<>(ResponseDTO.getResponse(
                    null, LocalDateTime.now(),HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value(),ErrorResponse.getErrorResponse(result)),HttpStatus.NOT_ACCEPTABLE);
        }
        EmployeeDTO updatedEmp=empService.updateEmployee(empDto);
        return new ResponseEntity<>(ResponseDTO.getResponse(updatedEmp, LocalDateTime.now(), HttpStatus.OK,HttpStatus.OK.value(), null),HttpStatus.OK);
    }
    @PostMapping("/getModifiedList")
    public ResponseEntity<?> getModifiedList(@RequestBody PaginationDTO paginationDTO){
        List<EmployeeDTO> employees=empService.getModifiedList(paginationDTO);
        return  new ResponseEntity<List<EmployeeDTO>>(employees, HttpStatus.OK);
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<EmployeeDTO> getEmpById(@PathVariable("id") Integer eid){
        EmployeeDTO foundEmp=empService.getById(eid);
        return  new ResponseEntity<EmployeeDTO>(foundEmp, HttpStatus.OK);
    }

    @GetMapping("/getByMobile")
    public ResponseEntity<EmployeeDTO> getEmpByMobile(@RequestParam(value = "mobile", required = false) String mobile){
        EmployeeDTO foundEmp=empService.getByMobile(mobile);
        return  new ResponseEntity<EmployeeDTO>(foundEmp, HttpStatus.OK);
    }


    @GetMapping("/getAllEmp")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee(){
        List<EmployeeDTO> employees=empService.getEmployeeList();
        return  new ResponseEntity<List<EmployeeDTO>>(employees, HttpStatus.OK);
    }

    @DeleteMapping("/deleteEmp")
    public ResponseEntity<String> deleteEmp(@RequestParam(value = "eid", required = false) Integer eid){
        empService.deleteById(eid);
        return new ResponseEntity<String>("Deleted",HttpStatus.OK);
    }

}
