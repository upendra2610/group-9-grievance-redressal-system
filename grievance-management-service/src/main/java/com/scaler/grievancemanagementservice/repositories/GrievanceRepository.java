package com.scaler.grievancemanagementservice.repositories;

import com.scaler.grievancemanagementservice.models.Grievance;
import com.scaler.grievancemanagementservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrievanceRepository extends JpaRepository<Grievance, Long>{

    List<Grievance> findAllByCreatedBy(User createdBy);
}
