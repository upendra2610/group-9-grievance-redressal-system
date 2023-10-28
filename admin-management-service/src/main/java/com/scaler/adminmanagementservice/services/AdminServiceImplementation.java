package com.scaler.adminmanagementservice.services;

import com.scaler.adminmanagementservice.dtos.AdminDto;
import com.scaler.adminmanagementservice.dtos.GenericAdminDto;
import com.scaler.adminmanagementservice.exceptions.AlreadyExistException;
import com.scaler.adminmanagementservice.exceptions.NotFoundException;
import com.scaler.adminmanagementservice.models.Admin;
import com.scaler.adminmanagementservice.repository.AdminRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AdminServiceImplementation implements AdminService {

    private final AdminRepository adminRepository;

    public AdminServiceImplementation(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    private Admin convertToAdmin(AdminDto adminDto) {
        Admin admin = new Admin();
        admin.setId(adminDto.getId());
        admin.setName(adminDto.getName());
        admin.setPassword((adminDto.getPassword()));
        admin.setEmail(adminDto.getEmail());
        return admin;
    }

    private AdminDto convertToAdminDto(Admin admin) {
        AdminDto adminDto = new AdminDto();
        adminDto.setId(admin.getId());
        adminDto.setName(admin.getName());
        adminDto.setPassword((admin.getPassword()));
        adminDto.setEmail(admin.getEmail());
        return adminDto;
    }


    private GenericAdminDto convertToGenericAdminDto(Admin admin) {
        GenericAdminDto genericAdminDto = new GenericAdminDto();
        genericAdminDto.setId(admin.getId());
        genericAdminDto.setName(admin.getName());
        genericAdminDto.setEmail(admin.getEmail());
        return genericAdminDto;
    }


    public GenericAdminDto getAdminById(Long id) throws NotFoundException {
        Optional<Admin> admin = adminRepository.findById(id);

        if (admin.isPresent()) {
            return convertToGenericAdminDto(admin.get());
        }
        throw new NotFoundException("Admin with id:" + id + " " + "doesn't exist");
    }


    public List<GenericAdminDto> getAllAdmins() {
        List<GenericAdminDto> genericAdmins = new ArrayList<>();
        List<Admin> admins = adminRepository.findAll();

        for (Admin a : admins) {
            GenericAdminDto adminDto = new GenericAdminDto();
            adminDto.setId(a.getId());
            adminDto.setEmail(a.getEmail());
            adminDto.setName(a.getName());
            genericAdmins.add(adminDto);
        }
        return genericAdmins;
    }


    public AdminDto createAdmin(AdminDto adminDto) throws AlreadyExistException {
        Admin admin = convertToAdmin(adminDto);
        if (!adminRepository.existsAdminByEmail(admin.getEmail())) {
            return convertToAdminDto(adminRepository.save(admin));
        }
        throw new AlreadyExistException("Email already exist");


    }


    public ResponseEntity<String> deleteAdminById(Long id) throws NotFoundException {
        Optional<Admin> admin = adminRepository.findById(id);

        if (admin.isPresent()) {
            adminRepository.deleteById(id);
            return new ResponseEntity<>("Admin with id: " + id + " deleted", HttpStatus.OK);
        }
        throw new NotFoundException("Admin with id: " + id + " doesn't exist");
    }


    public ResponseEntity<String> updateAdmin(Long id, AdminDto adminDto) throws NotFoundException {
        Optional<Admin> admin = adminRepository.findById(id);

        if (admin.isPresent()) {
            Admin newAdmin = admin.get();
            newAdmin.setName(adminDto.getName());
            newAdmin.setPassword(adminDto.getPassword());
            newAdmin.setEmail(adminDto.getEmail());
            adminRepository.save(newAdmin);
            return new ResponseEntity<>("Updated successfully", HttpStatus.ACCEPTED);
        }
        throw new NotFoundException("Admin with id: " + id + " not found to update");
    }
}
