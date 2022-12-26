package com.hostbooks.crud.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

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
    private boolean deleteFlag;

    private List<AddressDTO> addressList= new ArrayList<>();
    private BankingDetailsDTO bankingDetails;

}
