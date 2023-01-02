package com.hostbooks.crud.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {

    private Integer employeeId;
    private String imageUrl;
    private String name;
    private String department;
    private String mobile;
    private String emailId;
    private Long Salary;
    private String password;
    private Integer count;
    private boolean deleteFlag;
    private List<AddressDTO> addressList= new ArrayList<>();
    private BankingDetailsDTO bankingDetails;
    private Set<Role> roles=new HashSet<>();

}
