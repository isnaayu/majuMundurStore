package com.enigma.majuMundurStore.service.impl;

import com.enigma.majuMundurStore.constant.ERole;
import com.enigma.majuMundurStore.entity.Role;
import com.enigma.majuMundurStore.repository.RoleRepository;
import com.enigma.majuMundurStore.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public Role getOrSave(Role role) {
        Optional<Role> optionalRole = roleRepository.findByName(role.getName());
        if (!optionalRole.isEmpty()) return optionalRole.get();

        return roleRepository.save(role);
    }
}
