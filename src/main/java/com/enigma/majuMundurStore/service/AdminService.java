package com.enigma.majuMundurStore.service;

import com.enigma.majuMundurStore.dto.request.AdminRequest;
import com.enigma.majuMundurStore.dto.response.AdminResponse;
import com.enigma.majuMundurStore.entity.Admin;

import java.util.List;

public interface AdminService {
    AdminResponse create(AdminRequest adminRequest);
    AdminResponse update(AdminRequest adminRequest);
    List<Admin> getAll();
    AdminResponse getById(String id);
    void delete(String id);
    AdminResponse createNewAdmin(Admin adminRequest);
}
