package com.prabhat.blog.services;


import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {

    UserDetails authenticate(String email, String password); // This takes email,passwod from the user
    String generateToken(UserDetails userDetails); // This convert the above details into jwt token
    UserDetails validateToken(String token); // This converts the jwt token into userDetails to match the credentials
}
