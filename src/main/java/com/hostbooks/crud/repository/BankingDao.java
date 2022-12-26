package com.hostbooks.crud.repository;


import com.hostbooks.crud.models.BankingDetails;

import java.util.List;

public interface BankingDao {
    public BankingDetails updateBankDetails(BankingDetails bankingDetails);

    public BankingDetails findByBankId(Integer bankId);
}
