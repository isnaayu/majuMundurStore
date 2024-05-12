package com.enigma.majuMundurStore.repository;

import com.enigma.majuMundurStore.constant.ERole;
import com.enigma.majuMundurStore.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
