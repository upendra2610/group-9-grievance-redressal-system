package com.scaler.adminmanagementservice.repository;

import com.scaler.adminmanagementservice.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    boolean existsAdminByEmail(String email);

}

