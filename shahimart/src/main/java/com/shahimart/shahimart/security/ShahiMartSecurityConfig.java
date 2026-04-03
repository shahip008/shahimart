package com.shahimart.shahimart.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class ShahiMartSecurityConfig {
    private final List<String> publicPaths;

    @Value("${cors.allowed-origins}")
    private String allowedOrigins;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) {
        return http.csrf(csrfConfig -> csrfConfig.disable())
                .cors(corsConfig -> corsConfig.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests((requests) ->
//                                requests.requestMatchers(HttpMethod.GET, "/api/v1/products/**").permitAll()
//                                        .requestMatchers("/api/v1/products/**", "/api/v1/contacts/").permitAll()
//                                        .requestMatchers("/api/v1/dummy/**").authenticated()
//                                        .anyRequest().authenticated()
                        {
                            publicPaths.forEach(path -> requests.requestMatchers(path).permitAll());
                            requests.requestMatchers("/api/v1/dummy/**").hasAnyRole("ADMIN")

                                    .anyRequest().authenticated();
                        }

                )
                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .build();
//        http.formLogin(withDefaults());
//        http.httpBasic(withDefaults());
//        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var user1 = User.builder().username("Prince").password("$2a$12$XwFQblih9NHvwohrJRdb8OOUZjN8.LbjnKn/OlPI5OTSq0Yn8yxAe")
                .roles("USER").build();
        var user2 = User.builder().username("admin").password("$2a$12$YvfaOxNFsQ3gZGV40PhPae6dHH5a8K3iP0VB04vrFLfKSc2VpNhYO")
                .roles("USER", "ADMIN").build();
        log.debug("Before Returning userDetailsService user1 : " + user1 + " user2 : " + user2);
        return new InMemoryUserDetailsManager(user1, user2);
    }

    //    @Bean
//    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
//                                                       PasswordEncoder passwordEncoder) {
//
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
//
//        return new ProviderManager(daoAuthenticationProvider);
//    }
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
                                                       PasswordEncoder passwordEncoder) {
        log.debug("Entring into authenticationManager userDetailsService : " + userDetailsService + " passwordEncoder " + passwordEncoder);
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        log.debug("authenticationManager daoAuthenticationProvider : " + daoAuthenticationProvider);
        ProviderManager ProviderManager = new ProviderManager(daoAuthenticationProvider);
        log.debug("authenticationManager ProviderManager : " + ProviderManager);
        return ProviderManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList(allowedOrigins));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        config.setAllowCredentials(true);
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
