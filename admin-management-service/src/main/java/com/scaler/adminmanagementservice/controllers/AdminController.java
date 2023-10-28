package com.scaler.adminmanagementservice.controllers;

import com.scaler.adminmanagementservice.dtos.AdminDto;
import com.scaler.adminmanagementservice.dtos.GenericAdminDto;
import com.scaler.adminmanagementservice.exceptions.AlreadyExistException;
import com.scaler.adminmanagementservice.exceptions.NotFoundException;
import com.scaler.adminmanagementservice.services.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController implements AdminOperations {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {

        this.adminService = adminService;
    }


    public GenericAdminDto getAdminById(Long id) throws NotFoundException {
        return adminService.getAdminById(id);
    }


    public List<GenericAdminDto> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    public ResponseEntity<String> createAdmin(AdminDto adminDto) throws AlreadyExistException {
        adminService.createAdmin(adminDto);
        return new ResponseEntity<>("New admin created successfully", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteAdmin(Long id) throws NotFoundException {
        return adminService.deleteAdminById(id);
    }

    public ResponseEntity<String> updateAdminById(Long id, AdminDto adminDto) throws NotFoundException {
        return adminService.updateAdmin(id, adminDto);
    }
}
