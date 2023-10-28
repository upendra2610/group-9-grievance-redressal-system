package com.scaler.adminmanagementservice.controllers;

import com.scaler.adminmanagementservice.dtos.AdminDto;
import com.scaler.adminmanagementservice.dtos.GenericAdminDto;
import com.scaler.adminmanagementservice.exceptions.AlreadyExistException;
import com.scaler.adminmanagementservice.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/admins")
public interface AdminOperations {
    @GetMapping("{id}")
    public GenericAdminDto getAdminById(@PathVariable("id") Long id) throws NotFoundException;

    @GetMapping
    public List<GenericAdminDto> getAllAdmins();

    @PostMapping()
    public ResponseEntity<String> createAdmin(@RequestBody AdminDto admin) throws AlreadyExistException, AlreadyExistException;

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable("id") Long id) throws NotFoundException;

    @PutMapping("{id}")
    public ResponseEntity<String> updateAdminById(@PathVariable("id") Long id, @RequestBody AdminDto admin) throws NotFoundException;
}
