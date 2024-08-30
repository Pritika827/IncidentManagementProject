package com.transline.controllers;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transline.entities.JwtRequest;
import com.transline.entities.JwtResponse;
import com.transline.entities.User;
import com.transline.security.JwtHelper;
import com.transline.servicesImp.UserService;

import ch.qos.logback.classic.Logger;

//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//	@Autowired
//	private UserDetailsService userDetailsService;
//
//	@Autowired(required = true)
//	private AuthenticationManager manager;
//
//	@Autowired
//	private JwtHelper helper;
//
//	private Logger logger = (Logger) LoggerFactory.getLogger(AuthController.class);
//
//	@PostMapping("/login")
//	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
//
//		this.doAuthenticate(request.getEmail(), request.getPassword());
//
//		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
//		String token = this.helper.generateToken(userDetails);
//
//		JwtResponse response = JwtResponse.builder().token(token).username(userDetails.getUsername()).build();
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}
//
//	private void doAuthenticate(String email, String password) {
//
//		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
//		try {
//			manager.authenticate(authentication);
//
//		} catch (BadCredentialsException e) {
//			throw new BadCredentialsException(" Invalid Username or Password  !!");
//		}
//
//	}
//
//	@ExceptionHandler(BadCredentialsException.class)
//	public String exceptionHandler() {
//		return "Credentials Invalid !!";
//	}
//
//}



@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "false")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtHelper helper;
    
    @Autowired
    private UserService userService;

    private Logger logger = (Logger) LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getUserName(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .token(token).build();
            //    .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
    
    @PostMapping("/create-user")
    public User createUser(@RequestBody User user) {
    	return userService.createUser(user);
    }

}
