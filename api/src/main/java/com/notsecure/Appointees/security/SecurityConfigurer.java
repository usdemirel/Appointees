package com.notsecure.Appointees.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
@Autowired
private AppointeesUserDetailsService appointeesUserDetailsService;

@Autowired
JwtRequestFilter jwtRequestFilter;

@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
   auth.userDetailsService(appointeesUserDetailsService);
}

@Override
public void configure(WebSecurity web) throws Exception {
   web
                   .ignoring()
                   .antMatchers("/h2/**");
}

@Override
protected void configure(HttpSecurity http) throws Exception {
   http.csrf().disable()
                   
                   .authorizeRequests()
                   .antMatchers("/authenticate","/public/**").permitAll()
                   .anyRequest().authenticated()
                   .and().sessionManagement()
                   .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
   http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
}

@Bean
public PasswordEncoder passwordEncoder() {
   return NoOpPasswordEncoder.getInstance(); //TODO: change it with bcrypt
}

@Bean
public AuthenticationManager authenticationManagerBean() throws Exception {
   return super.authenticationManagerBean();
}

}
