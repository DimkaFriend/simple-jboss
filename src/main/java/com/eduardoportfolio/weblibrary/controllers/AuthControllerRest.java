package com.eduardoportfolio.weblibrary.controllers;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.eduardoportfolio.weblibrary.dao.RoleDao;
import com.eduardoportfolio.weblibrary.dao.UserDao;
import com.eduardoportfolio.weblibrary.exception.AppException;
import com.eduardoportfolio.weblibrary.models.Role;
import com.eduardoportfolio.weblibrary.models.Users;
import com.eduardoportfolio.weblibrary.payload.ApiResponse;
import com.eduardoportfolio.weblibrary.payload.JwtAuthenticationResponse;
import com.eduardoportfolio.weblibrary.payload.LoginRequest;
import com.eduardoportfolio.weblibrary.payload.SignUpRequest;
import com.eduardoportfolio.weblibrary.security.JwtTokenProvider;

@Controller
@RequestMapping("/rest")
public class AuthControllerRest {
	
	Logger logger = LoggerFactory.getLogger(AuthControllerRest.class);
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDao userRepository;

    @Autowired
    RoleDao roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;
    
//	@RequestMapping(name = "/login", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
//	@RequestMapping(value = "/login",method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
//	public  @ResponseBody User loginPage(){
//		User user = new User();
//		user.setUserName("asdasdas");
//		return user;
//	}
	
	@RequestMapping(value = "/signin",method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }
	
	@RequestMapping(value = "/signup",method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
//	    public @ResponseBody User registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
//		User user = new User();
//		user.setName(signUpRequest.getName());
//		user.setLogin(signUpRequest.getUsername());
//		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
//		Role userRole = roleRepository.getRole("USER");
//		user.setRoles(Collections.singleton(userRole)); 
		
		logger.info("/signup");
    	if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        Users user = new Users();//new User(signUpRequest.getName(), signUpRequest.getUsername(),signUpRequest.getEmail(), signUpRequest.getPassword());
        user.setLogin(signUpRequest.getUsername());
        user.setName(signUpRequest.getName());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        Role userRole = roleRepository.getRole("USER");
//                .orElseThrow(() -> new AppException("User Role not set."));
        if(userRole==null) {throw new AppException("User Role not set.");}
        
        user.setRoles(Collections.singleton(userRole)); 

        userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/rest/signin")
                .build().toUri();
//        return user;
        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}
