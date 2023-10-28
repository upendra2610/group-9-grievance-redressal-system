package com.scaler.grievancemanagementservice.controllers;

import com.scaler.grievancemanagementservice.dtos.GrievanceRequestDto;
import com.scaler.grievancemanagementservice.dtos.GrievanceResponseDto;
import com.scaler.grievancemanagementservice.models.Grievance;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/grievances")
public interface GrievanceOperations {

    @GetMapping("/")
    List<GrievanceResponseDto> getAllGrievances();

    @GetMapping("/{id}")
    GrievanceResponseDto getGrievanceById(@PathVariable Long id);

    @PostMapping("/{id}")
    public void createGrievance(@RequestBody GrievanceRequestDto grievanceRequestDto);

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id);

    @PutMapping("/{id}")
    void update(@RequestBody GrievanceRequestDto grievanceRequestDto);


}
