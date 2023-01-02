package com.hostbooks.crud.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements UserDetails  {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer employeeId;

    private String imageUrl;
    private String name;

    private String department;
    private String mobile;
    private String emailId;
    private Long Salary;
    private String password;
    private boolean deleteFlag;
    @OneToMany(cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JoinColumn(name = "addId")
    private List<Address> addressList= new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JoinColumn(name = "bankId")
    private BankingDetails bankingDetails;

    @ManyToMany(cascade = CascadeType.ALL,
    fetch = FetchType.EAGER)
    private Set<Role> roles=new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities=this.roles.stream().map((role)-> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.emailId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
