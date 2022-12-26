package com.hostbooks.crud.controller;

import com.hostbooks.crud.exceptions.AddressException;
import com.hostbooks.crud.exceptions.EmployeeException;
import com.hostbooks.crud.models.Address;
import com.hostbooks.crud.models.AddressDTO;
import com.hostbooks.crud.models.EmployeeDTO;
import com.hostbooks.crud.services.AddressService;
import com.hostbooks.crud.services.EmpService;
import org.modelmapper.internal.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/hostbooks/address")
@CrossOrigin(origins="*")
public class AddressController {
    @Autowired
    public AddressService addService;
    @Autowired
    public EmpService empService;

//    @PostMapping("/save")
//    public ResponseEntity<AddressDTO> addAddress(@Valid @RequestBody AddressDTO addDto) throws EmployeeException, AddressException {
//
//        AddressDTO addedAdd=addService.addAddress(addDto);
//        return new ResponseEntity<AddressDTO>(addedAdd, HttpStatus.OK);
//    }
//
//    @GetMapping("/getByEmpId/{id}")
//    public ResponseEntity<List<AddressDTO>> getEmpByEmpId(@PathVariable("id") Integer eid) throws EmployeeException, AddressException {
//        List<AddressDTO> address=addService.getAddressByEmpId(eid);
//        return  new ResponseEntity<List<AddressDTO>>(address, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/delete")
//    public ResponseEntity<String> deleteById(@RequestParam(value = "empId", required = false) Integer empId, @RequestParam(value = "addId", required = false) Integer addId) throws EmployeeException, AddressException {
//        addService.deleteAddress(addId,empId);
//        return new ResponseEntity<String>("Deleted",HttpStatus.OK);
//    }
}
