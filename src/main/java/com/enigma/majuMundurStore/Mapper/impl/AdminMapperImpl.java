package com.enigma.majuMundurStore.Mapper.impl;

import com.enigma.majuMundurStore.Mapper.AdminMapper;
import com.enigma.majuMundurStore.dto.request.AdminRequest;
import com.enigma.majuMundurStore.dto.response.AdminResponse;
import com.enigma.majuMundurStore.entity.Admin;
import org.springframework.stereotype.Component;

@Component
public class AdminMapperImpl implements AdminMapper {
    @Override
    public Admin mapToAdminrRequest(AdminRequest adminRequest) {
        return Admin.builder()
                .name(adminRequest.getName())
                .email(adminRequest.getEmail())
                .build();
    }

    @Override
    public AdminResponse mapToAdminResponse(Admin admin) {
        return AdminResponse.builder()
                .id(admin.getId())
                .AdminName(admin.getName())
                .email(admin.getEmail())
                .build();
    }
}
