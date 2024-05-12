package com.enigma.majuMundurStore.service.impl;

import com.enigma.majuMundurStore.Mapper.AdminMapper;
import com.enigma.majuMundurStore.dto.request.AdminRequest;
import com.enigma.majuMundurStore.dto.response.AdminResponse;
import com.enigma.majuMundurStore.dto.response.CustomerResponse;
import com.enigma.majuMundurStore.entity.Admin;
import com.enigma.majuMundurStore.entity.Customer;
import com.enigma.majuMundurStore.repository.AdminRepository;
import com.enigma.majuMundurStore.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    @Override
    public AdminResponse create(AdminRequest adminRequest) {
        Admin admin = adminMapper.mapToAdminrRequest(adminRequest);
        adminRepository.save(admin);
        return adminMapper.mapToAdminResponse(admin);
    }

    @Override
    public AdminResponse update(AdminRequest adminRequest) {
        AdminResponse adminResponse = getById(adminRequest.getId());
        if (adminResponse != null){
            Admin admin = adminMapper.mapToAdminrRequest(adminRequest);
            admin.setId(adminResponse.getId());
            adminRepository.save(admin);
            return adminMapper.mapToAdminResponse(admin);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin Not Found");
        }
    }

    @Override
    public List<Admin> getAll() {
        return adminRepository.findAll().stream()
                .map((admin -> Admin.builder()
                        .id(admin.getId())
                        .name(admin.getName())
                        .email(admin.getEmail())
                        .build()))
                .collect(Collectors.toList());
    }

    @Override
    public AdminResponse getById(String id) {
        return adminRepository.findById(id)
                .map((admin -> {
                   AdminResponse adminResponse = new AdminResponse();
                   adminResponse.setId(admin.getId());
                   adminResponse.setAdminName(admin.getName());
                   adminResponse.setEmail(admin.getEmail());
                   return adminResponse;
                }))
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin Not Found"));
    }

    @Override
    public void delete(String id) {
        if (getById(id) != null){
            adminRepository.deleteById(id);
            System.out.println("Successfully Deleted Admin");
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin Not Found");
        }
    }

    @Override
    public AdminResponse createNewAdmin(Admin request) {
        Admin admin = adminRepository.saveAndFlush(request);
        return AdminResponse.builder()
                .id(admin.getId())
                .AdminName(admin.getName())
                .email(admin.getEmail())
                .build();
    }
}
