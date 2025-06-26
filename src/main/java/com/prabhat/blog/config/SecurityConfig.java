package com.prabhat.blog.config;

import com.prabhat.blog.domain.entities.User;
import com.prabhat.blog.repositories.UserRepository;
import com.prabhat.blog.security.BlogUserDetailsService;
import com.prabhat.blog.security.JwtAuthenticationFilter;
import com.prabhat.blog.services.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    //Use the JwtAuthenticationFilter from security package
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthenticationService authenticationService){
        return new JwtAuthenticationFilter(authenticationService);
    }

    //Above method needs UserDetails
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        BlogUserDetailsService blogUserDetailsService = new BlogUserDetailsService(userRepository);

        // Not for the production but to try the working
        String email = "prabhatadvait@gmail.com";
        userRepository.findByEmail(email).orElseGet(()-> {
            User newUser = User.builder()
                    .name("Prabhat Sharma")
                    .email(email)
                    .password(passwordEncoder().encode("Pk@766712"))
                    .build();
            return userRepository.save(newUser);
        });
        return blogUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception{
        http
                .cors(cors -> {})
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/posts/drafts").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/v1/posts/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/categories/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/tags/**").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }
}

//| Bean                      | What it does                                   | Why it’s needed                                                      |
//| ------------------------- | ---------------------------------------------- | -------------------------------------------------------------------- |
//| `passwordEncoder()`       | Registers a password encoder (usually BCrypt)  | Needed to hash and verify passwords securely                         |
//| `authenticationManager()` | Exposes Spring’s built-in authentication logic | Needed to manually authenticate login requests (e.g., for JWT login) |
