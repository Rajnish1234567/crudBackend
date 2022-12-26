package com.hostbooks.crud.services;

import com.hostbooks.crud.exceptions.BankingException;
import com.hostbooks.crud.exceptions.EmployeeException;
import com.hostbooks.crud.models.BankingDetailsDTO;
import com.hostbooks.crud.models.BankingDetails;
import com.hostbooks.crud.repository.BankingDao;
import com.hostbooks.crud.repository.EmpDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankingServiceImpl implements BankingService{
    @Autowired
    public BankingDao bankingDao;
    @Autowired
    public ModelMapper modelMapper;

    @Override
    public BankingDetailsDTO updateBankDetails(BankingDetailsDTO bdto){
        return this.convertToDTO(bankingDao.updateBankDetails(this.convertToEntity(bdto)));
    }

    @Override
    public BankingDetailsDTO getBankDetailsByBankId(Integer bankId) {
        return this.convertToDTO( bankingDao.findByBankId(bankId));
    }


    public BankingDetailsDTO convertToDTO(BankingDetails bd) {
        return this.modelMapper.map(bd, BankingDetailsDTO.class);
    }

    public BankingDetails convertToEntity(BankingDetailsDTO bDto){
        return this.modelMapper.map(bDto,BankingDetails.class);
    }
}
