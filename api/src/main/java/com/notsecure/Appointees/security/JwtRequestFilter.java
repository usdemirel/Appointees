package com.notsecure.Appointees.security;

import com.notsecure.Appointees.controller.AppointmentController;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

@Autowired
private AppointeesUserDetailsService appointeesUserDetailsService;

Logger logger = LoggerFactory.getLogger(AppointeesUserDetailsService.class);

@Autowired
private JwtUtil jwtTokenUtil;


@Override
protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
   final String authorizationHeader = httpServletRequest.getHeader("Authorization");
   
   String email = null;
   String jwt = null;
   
   if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      jwt = authorizationHeader.substring(7);//5
      try{
         email = jwtTokenUtil.extractUsername(jwt);
      }catch (SignatureException e){
         //TODO: Properly Log this activity: No need to throw error!
         logger.warn("SignatureException");
      }
   }
   
   if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = this.appointeesUserDetailsService.loadUserByUsername(email);
      if (jwtTokenUtil.validateToken(jwt, userDetails)) {
         UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); //19//20
         usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
         SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }
   }
   filterChain.doFilter(httpServletRequest,httpServletResponse);
}
}
