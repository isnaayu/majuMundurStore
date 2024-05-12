package com.enigma.majuMundurStore.service;

import com.enigma.majuMundurStore.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AppUser loadUserByUserId(String id);
}
