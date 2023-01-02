package com.hostbooks.crud.services;

import com.hostbooks.crud.exceptions.EmployeeException;
import com.hostbooks.crud.models.Employee;
import com.hostbooks.crud.models.EmployeeDTO;
import com.hostbooks.crud.models.PaginationDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmpService {
    public EmployeeDTO addEmployee(EmployeeDTO eDto);

    public EmployeeDTO updateEmployee(EmployeeDTO empDto) ;

    public EmployeeDTO getById(Integer id) ;

    public EmployeeDTO getByMobile(String mobile);

    public List<EmployeeDTO> getEmployeeList() ;
    public List<EmployeeDTO> getModifiedList(PaginationDTO paginationDTO);

    public void deleteById(Integer id);
}
