package com.example.booking.security;

import com.example.booking.services.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Autowired
    private CustomUserDetailService userDetailsService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                // Authorize requests configuration
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/","/api/login","/api/register", "/error").permitAll()
                        .anyRequest().authenticated()
                )

                // Custom login form configuration
//                .formLogin(form -> form
//                        .loginPage("/login") // Custom login page
//                        .defaultSuccessUrl("/home", true)
//                        .permitAll()
//                )
                // Logout configuration
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll()
                );
                // OAuth2 login configuration
//                .oauth2Login(oauth2 -> oauth2
//                        .loginPage("/login") // Use the same custom login page
//                        .defaultSuccessUrl("/home", true)
//                        .failureUrl("/login?error=true")
//                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService); // Using customUserDetailsService to fetch user details
        authProvider.setPasswordEncoder(new BCryptPasswordEncoder()); // Setting the password encoder

        return new ProviderManager(List.of(authProvider)); // Using ProviderManager with the authProvider
    }


}
