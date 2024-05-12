package com.enigma.majuMundurStore.service;

import com.enigma.majuMundurStore.entity.Role;

public interface RoleService {
    Role getOrSave(Role role);
}
