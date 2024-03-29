package com.springboot.blog.controller;

import com.springboot.blog.entity.Role;
import com.springboot.blog.entity.User;
import com.springboot.blog.payload.JWTResponseDto;
import com.springboot.blog.payload.LoginDto;
import com.springboot.blog.payload.SignUpDto;
import com.springboot.blog.repository.RoleRepository;
import com.springboot.blog.repository.UserRepository;
import com.springboot.blog.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "REST APIs for Auth Resource")
public class AuthController {


    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = {"/login", "/signin"})
    @Operation(

            description = "login in the application by passing email and password and receive JWT Token",
            summary = "Login with your credential",
            responses = {
                    @ApiResponse(
                            description = "Login Successfully",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized or Invalid Token",
                            responseCode = "401"
                    )
            }
    )
    public ResponseEntity<JWTResponseDto> authenticateUser(@RequestBody LoginDto loginDto) {

        JWTResponseDto jwtResponseDto = new JWTResponseDto();


        String token = authService.login(loginDto);
        jwtResponseDto.setAccessToken(token);


        return ResponseEntity.ok(jwtResponseDto);
    }


    @PostMapping(value = {"/signup", "/register"})
    @Operation(

            description = "Register new User by sending (name , email , password , username ) as Parameters",
            summary = "Create New User",
            responses = {
                    @ApiResponse(
                            description = "CREATED",
                            responseCode = "201"
                    ),
                    @ApiResponse(
                            description = "Bad Request , Existing Email or bad info",
                            responseCode = "404"
                    )
            }
    )
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {


        String register = authService.register(signUpDto);
        return new ResponseEntity<>(register, HttpStatus.CREATED);

    }


}
