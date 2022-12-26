package com.hostbooks.crud.controller;
import com.hostbooks.crud.models.BankingDetailsDTO;
import com.hostbooks.crud.services.BankingService;
import com.hostbooks.crud.services.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("hostbooks/bank")
@CrossOrigin("*")
public class BankingDetailsController {

    @Autowired
    public BankingService bankService;

    @Autowired
    public EmpService empService;

    @PutMapping("/update")
    public ResponseEntity<BankingDetailsDTO> updateBankDetails(@Valid @RequestBody BankingDetailsDTO bankDetails) {
        BankingDetailsDTO saved=bankService.updateBankDetails(bankDetails);
        return new ResponseEntity<BankingDetailsDTO>(saved, HttpStatus.OK);
    }

    @GetMapping("/getByBankId")
    public ResponseEntity<BankingDetailsDTO> getDetailsByBankId(@RequestParam(value = "bankId") Integer bankId){
        BankingDetailsDTO details=bankService.getBankDetailsByBankId(bankId);
        return new ResponseEntity<BankingDetailsDTO>(details,HttpStatus.OK);
    }


}
