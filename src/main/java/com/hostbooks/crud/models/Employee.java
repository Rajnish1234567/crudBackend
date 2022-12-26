package com.hostbooks.crud.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer employeeId;

    private String imageUrl;
    private String name;

    private String department;
    private String mobile;

    private String emailId;

    private Long Salary;

    private boolean deleteFlag;
    @OneToMany(cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JoinColumn(name = "addId")
    private List<Address> addressList= new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JoinColumn(name = "bankId")
    private BankingDetails bankingDetails;

}
