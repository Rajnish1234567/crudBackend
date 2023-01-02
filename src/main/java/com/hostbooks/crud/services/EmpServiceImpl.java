package com.hostbooks.crud.services;

import com.hostbooks.crud.exceptions.EmployeeException;
import com.hostbooks.crud.models.*;
import com.hostbooks.crud.repository.AddressDao;
import com.hostbooks.crud.repository.BankingDao;
import com.hostbooks.crud.repository.EmpDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpServiceImpl implements EmpService{

    @Autowired
    private EmpDao edao;
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private BankingDao bankingDao;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public EmployeeDTO addEmployee(EmployeeDTO empDto){
        Employee emp=this.convertToEntity(empDto);
        return this.convertToDTO(edao.addEmployee(emp));
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO empDto){
        Employee emp=this.convertToEntity(empDto);
        return this.convertToDTO(edao.updateEmployee(emp));
    }

    @Override
    public EmployeeDTO getById(Integer id) {
        return this.convertToDTO(edao.findByEmpId(id));
    }

    @Override
    public EmployeeDTO getByMobile(String mobile){
        return this.convertToDTO(edao.findByMobile(mobile));
    }

    @Override
    public List<EmployeeDTO> getEmployeeList() {
        List<Employee> employees=edao.getEmployeeList();
        List<EmployeeDTO> lists=new ArrayList<>();
        for(Employee emp: employees)lists.add(this.convertToDTO(emp));
        return lists;
    }

    @Override
    public List<EmployeeDTO> getModifiedList(PaginationDTO paginationDTO) {
        List<Employee> employees=edao.listEmployee(paginationDTO);
        List<EmployeeDTO> lists=new ArrayList<>();
        for(Employee emp: employees)lists.add(this.convertToDTO(emp));
        return lists;
    }

    @Override
    public void deleteById(Integer id) {
        edao.deleteByEmpId(id);
    }



    public EmployeeDTO convertToDTO(Employee emp){
        EmployeeDTO eDto=this.modelMapper.map(emp,EmployeeDTO.class);
        return eDto;
    }

    public Employee convertToEntity(EmployeeDTO eDto){
        return this.modelMapper.map(eDto,Employee.class);
    }

}
