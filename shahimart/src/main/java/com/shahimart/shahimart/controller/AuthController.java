package com.shahimart.shahimart.controller;

import com.shahimart.shahimart.dto.LoginRequestDto;
import com.shahimart.shahimart.dto.LoginResponseDto;
import com.shahimart.shahimart.dto.RegisterRequestDto;
import com.shahimart.shahimart.dto.UserDto;
import com.shahimart.shahimart.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> apiLogin(@RequestBody LoginRequestDto loginRequestDto) {
        log.debug("Enteing into apiLogin method in class AuthController");
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDto.username(), loginRequestDto.password()
                    ));
            var userDto = new UserDto();
            var loggedInUser = (User) authentication.getPrincipal();
            log.debug("loggedInUser : " + loggedInUser + " getUsername :" + loggedInUser.getUsername());
            userDto.setName(loggedInUser.getUsername());
            String jwtToken = jwtUtil.generateJwtToken(authentication);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new LoginResponseDto(HttpStatus.OK.getReasonPhrase(), userDto, jwtToken));
        } catch (BadCredentialsException e) {
            return buildResponseEntity(HttpStatus.UNAUTHORIZED,
                    "Invalid Username & Password");
        } catch (AuthenticationException e) {
            return buildResponseEntity(HttpStatus.UNAUTHORIZED,
                    "Authentication Failed");
        } catch (Exception e) {
            return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An unexpected error occurred");
        }
    }

    public ResponseEntity<LoginResponseDto> buildResponseEntity(HttpStatus status, String message) {
        return ResponseEntity.status(status)
                .body(new LoginResponseDto(message, null, null));
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
        inMemoryUserDetailsManager.createUser(new User(registerRequestDto.getEmail(),
                passwordEncoder.encode(registerRequestDto.getPassword()),
                List.of(new SimpleGrantedAuthority("USER"))));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Registration Successfull");
    }
}
