package com.hostbooks.crud.repository;

import com.hostbooks.crud.models.Address;
import com.hostbooks.crud.models.AddressDTO;
import com.hostbooks.crud.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressDao extends JpaRepository <Address,Integer> {
//    @Query("from Address a where a.employee.employeeId=:id")
//    public List<Address> findByEmployeeId(Integer id);
}
