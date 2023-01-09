package com.hostbooks.crud.controller;

import com.hostbooks.crud.security.JwtAuthRequest;
import com.hostbooks.crud.security.JwtAuthResponse;
import com.hostbooks.crud.security.JwtTokenHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hostbooks/auth")
@CrossOrigin(origins="*")
public class AuthController {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
        this.authenticate(request.getUsername(),request.getPassword());
        UserDetails userDetails=this.userDetailsService.loadUserByUsername(request.getUsername());
        String token=this.jwtTokenHelper.generateToken(userDetails);
        JwtAuthResponse response=new JwtAuthResponse();
        response.setToken(token);
        return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password);
        try{
            this.authenticationManager.authenticate(authenticationToken);
        }
        catch (BadCredentialsException bce){
            throw new Exception("Bad credentials");
        }
    }

}
