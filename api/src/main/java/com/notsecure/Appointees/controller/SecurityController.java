package com.notsecure.Appointees.controller;

import com.notsecure.Appointees.security.AppointeesUserDetailsService;
import com.notsecure.Appointees.security.AuthenticationRequest;
import com.notsecure.Appointees.security.AuthenticationResponse;
import com.notsecure.Appointees.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;

@RestController
public class SecurityController {

@Autowired
AuthenticationManager authenticationManager;

@Autowired
AppointeesUserDetailsService appointeesUserDetailsService;

@Autowired
JwtUtil jwtTokenUtil;

@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
   System.out.println("-> " + authenticationRequest.getEmail() + authenticationRequest.getPassword());
   try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
   } catch (BadCredentialsException e) {
      throw new Exception("Incorrect username password combination", e);
   }
   final UserDetails userDetails = appointeesUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());
   final String jwt = jwtTokenUtil.generateToken(userDetails);
   
   return ResponseEntity.ok(new AuthenticationResponse(jwt));
}
}
