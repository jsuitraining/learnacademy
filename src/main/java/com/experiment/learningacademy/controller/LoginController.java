package com.experiment.learningacademy.controller;

import com.experiment.learningacademy.entity.LoginEntity;
import com.experiment.learningacademy.entity.LoginResponseEntity;
import com.experiment.learningacademy.security.JwtTokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {


    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtility jwtTokenUtility;


    @PostMapping("/login")
    public ResponseEntity<LoginResponseEntity> login(@RequestBody LoginEntity loginEntity){

        this.doAuthentication(loginEntity.getUsername(),loginEntity.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginEntity.getUsername());
        String token = this.jwtTokenUtility.generateToken(userDetails);

        LoginResponseEntity loginResponseEntity = LoginResponseEntity.builder()
                .jwtToken(token).username(userDetails.getUsername()).build();


        return new ResponseEntity<>(loginResponseEntity, HttpStatus.OK);

    }

    private void doAuthentication(String username, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,password);
        try {
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException badCredentialsException){
            throw new BadCredentialsException("invalid credentials exception");
        }
    }
}
