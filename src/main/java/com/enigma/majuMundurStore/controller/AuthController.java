package com.enigma.majuMundurStore.controller;

import com.enigma.majuMundurStore.constant.AppPath;
import com.enigma.majuMundurStore.dto.request.AuthRequest;
import com.enigma.majuMundurStore.dto.request.OrderRequest;
import com.enigma.majuMundurStore.dto.response.CommonResponse;
import com.enigma.majuMundurStore.dto.response.LoginResponse;
import com.enigma.majuMundurStore.dto.response.OrderResponse;
import com.enigma.majuMundurStore.dto.response.RegisterResponse;
import com.enigma.majuMundurStore.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.AUTH)
public class AuthController {
    private final AuthService authService;
    @PostMapping("/register")
    public RegisterResponse create(@RequestBody AuthRequest authRequest){
        return authService.registerCustomer(authRequest);
    }

    @PostMapping("/register/admin")
    public RegisterResponse createAdmin(@RequestBody AuthRequest authRequest){
        return authService.registerAdmin(authRequest);
    }

    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        LoginResponse loginResponse = authService.login(authRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .data(loginResponse)
                        .message("Succesfully Login")
                        .statusCode(HttpStatus.CREATED.value())
                        .build());
    }
}
