package com.hostbooks.crud.security;

import com.hostbooks.crud.models.Employee;
import com.hostbooks.crud.repository.EmpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    private EmpDao empDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee emp=this.empDao.findByEmail(username);
        return emp;
    }
}
