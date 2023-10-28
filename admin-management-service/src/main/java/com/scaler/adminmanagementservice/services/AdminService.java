package com.scaler.adminmanagementservice.services;

import com.scaler.adminmanagementservice.dtos.AdminDto;
import com.scaler.adminmanagementservice.dtos.GenericAdminDto;
import com.scaler.adminmanagementservice.exceptions.AlreadyExistException;
import com.scaler.adminmanagementservice.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminService {
    GenericAdminDto getAdminById(Long id) throws NotFoundException;

    List<GenericAdminDto> getAllAdmins();

    AdminDto createAdmin(AdminDto adminDto) throws AlreadyExistException;

    ResponseEntity<String> deleteAdminById(Long id) throws NotFoundException;

    ResponseEntity<String> updateAdmin(Long id, AdminDto adminDto) throws NotFoundException;

}
