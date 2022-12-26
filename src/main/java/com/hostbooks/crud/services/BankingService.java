package com.hostbooks.crud.services;

import com.hostbooks.crud.exceptions.BankingException;
import com.hostbooks.crud.exceptions.EmployeeException;
import com.hostbooks.crud.models.BankingDetails;
import com.hostbooks.crud.models.BankingDetailsDTO;

public interface BankingService {

    public BankingDetailsDTO updateBankDetails(BankingDetailsDTO bankDetails);

    public BankingDetailsDTO getBankDetailsByBankId(Integer bankId);
}
