package com.enigma.majuMundurStore.service;

import com.enigma.majuMundurStore.dto.request.AuthRequest;
import com.enigma.majuMundurStore.dto.response.LoginResponse;
import com.enigma.majuMundurStore.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse registerCustomer(AuthRequest authRequest);

    LoginResponse login(AuthRequest authRequest);

    RegisterResponse registerAdmin(AuthRequest authRequest);
}
