package com.hostbooks.crud.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class BankingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bankId;

    private String panCardNo;

    private String aadharNo;
    private String accountNo;


}
