package com.hostbooks.crud.repository;

import com.hostbooks.crud.models.Employee;
import com.hostbooks.crud.models.PaginationDTO;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface EmpDao {
    public Employee addEmployee(Employee employee);

    public  Employee updateEmployee(Employee employee);

    public List<Employee> getEmployeeList();

    public List<Employee> listEmployee(PaginationDTO paginationDTO);

    public Employee findByEmpId(Integer empId);
    public Employee findByMobile(String mobile);
    public Employee findByEmail(String email);

    public void deleteByEmpId(Integer empId);

}
