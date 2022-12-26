package com.hostbooks.crud.services;

import com.hostbooks.crud.exceptions.AddressException;
import com.hostbooks.crud.exceptions.EmployeeException;
import com.hostbooks.crud.models.Address;
import com.hostbooks.crud.models.AddressDTO;
import com.hostbooks.crud.repository.AddressDao;
import com.hostbooks.crud.repository.EmpDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    public ModelMapper modelMapper;
    @Autowired
    public EmpDao eDao;
    @Autowired
    public AddressDao addDao;
//    @Override
//    public AddressDTO addAddress(AddressDTO address) throws AddressException {
//        if(eDao.findById(address.getEmployee()).isPresent()){
//            Address add=this.convertToEntity(address);
//            System.out.println(add);
//            add.setEmployee(eDao.findById(address.getEmployee()).get());
//
//
//            return this.convertToDTO(addDao.save(add));
//        }
//        else throw new AddressException("No employee exist with employee_id"+address.getEmployee());
//
//    }
//
//    @Override
//    public String deleteAddress(Integer addressId, Integer eId) throws AddressException, EmployeeException {
//        Optional<Address> opt=addDao.findById(addressId);
//        if(opt.isPresent()){
//            if(opt.get().getEmployee().getEmployeeId()==eId){
//                addDao.deleteById(addressId);
//                return "Deleted successfully";
//            }
//            else throw new EmployeeException("employee id is invalid");
//        }
//        else throw new AddressException("This address does not exist in database");
//    }
//
//    @Override
//    public List<AddressDTO> getAddressByEmpId(Integer empId) throws AddressException {
//        List<Address> lists=addDao.findByEmployeeId(empId);
//        if(lists.isEmpty()) throw new AddressException("There is no address with employeeId"+empId);
//        else {
//            List<AddressDTO> dtos=new ArrayList<>();
//            for(Address add: lists) dtos.add(this.convertToDTO(add));
//            return dtos;
//        }
//    }



    public AddressDTO convertToDTO(Address add){
        return this.modelMapper.map(add,AddressDTO.class);
    }

    public Address convertToEntity(AddressDTO aDto){
        return this.modelMapper.map(aDto,Address.class);
    }
}
