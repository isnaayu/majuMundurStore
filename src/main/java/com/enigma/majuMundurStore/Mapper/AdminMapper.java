package com.enigma.majuMundurStore.Mapper;

import com.enigma.majuMundurStore.dto.request.AdminRequest;
import com.enigma.majuMundurStore.dto.response.AdminResponse;
import com.enigma.majuMundurStore.entity.Admin;

public interface AdminMapper {
    Admin mapToAdminrRequest(AdminRequest adminRequest);
    AdminResponse mapToAdminResponse(Admin admin);
}
