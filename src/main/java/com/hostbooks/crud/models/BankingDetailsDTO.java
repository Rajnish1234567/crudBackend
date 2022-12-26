package com.hostbooks.crud.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BankingDetailsDTO {
    private Integer bankId;

    private String panCardNo;

    private String aadharNo;
    private String accountNo;
}
