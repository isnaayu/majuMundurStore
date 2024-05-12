package com.enigma.majuMundurStore.controller;

import com.enigma.majuMundurStore.constant.AppPath;
import com.enigma.majuMundurStore.dto.request.AdminRequest;
import com.enigma.majuMundurStore.dto.response.AdminResponse;
import com.enigma.majuMundurStore.dto.response.CommonResponse;
import com.enigma.majuMundurStore.entity.Admin;
import com.enigma.majuMundurStore.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.ADMIN)
public class AdminController {
    private final AdminService adminService;
    @PostMapping
    public ResponseEntity<?> create(@RequestBody AdminRequest adminRequest){
        AdminResponse adminResponse = adminService.create(adminRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<AdminResponse>builder()
                                .statusCode(HttpStatus.CREATED.value())
                                .message("Successfully Created Admin")
                                .data(adminResponse)
                                .build()
                );
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody AdminRequest adminRequest){
        AdminResponse adminResponse = adminService.update(adminRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<AdminResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Update Admin")
                        .data(adminResponse)
                        .build()
                );
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<Admin> adminResponse = adminService.getAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Get All Admin")
                        .data(adminResponse)
                        .build()
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        AdminResponse adminResponse = adminService.getById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Get By Id Admin")
                        .data(adminResponse)
                        .build()
                );
    }

    @DeleteMapping
    public void delete(String id){
        adminService.delete(id);
    }
}
