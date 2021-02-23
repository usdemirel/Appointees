package com.notsecure.Appointees.security;

import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {
@Value( "${SECRET_KEY}" )
private String SECRET_KEY; //TODO: Should be pulled from the properties file. Plus, it needs more complexity.

public String extractUsername(String token)  throws SignatureException{
   return extractClaim(token, Claims::getSubject);//6 //11
}

public Date extractExpiration(String token) {
   return extractClaim(token, Claims::getExpiration); //16
}

public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) throws SignatureException {
   final Claims claims = extractAllClaims(token);
   return claimsResolver.apply(claims); //8 /13 //18
}

private Claims extractAllClaims(String token) throws SignatureException{
      return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody(); //7 //12 //17
}

private Boolean isTokenExpired(String token) {
   return extractExpiration(token).before(new Date()); //15
}

public String generateToken(UserDetails userDetails) {
   Map<String, Object> claims = new HashMap<>();
   //TODO: Can pass in specific claims here.. As a second thought, there is no need for it.
   return createToken(claims, userDetails.getUsername());
}

private String createToken(Map<String, Object> claims, String subject) {
   //subject is the person who is being authenticated
   return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                   .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10000)) //10,000 hours for now.
                   .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
}

public Boolean validateToken(String token, UserDetails userDetails) {
   final String username = extractUsername(token); //10 //14
   return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
}

   
}
