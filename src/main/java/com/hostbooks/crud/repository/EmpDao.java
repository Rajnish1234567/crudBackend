package com.hostbooks.crud.repository;

import com.hostbooks.crud.models.Employee;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface EmpDao {
    public Employee addEmployee(Employee employee);

    public  Employee updateEmployee(Employee employee);

    public List<Employee> getEmployeeList();

    public Employee findByEmpId(Integer empId);
    public Employee findByMobile(String mobile);

    public void deleteByEmpId(Integer empId);

}
