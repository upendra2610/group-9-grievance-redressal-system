package com.scaler.grievancemanagementservice.controllers;

import com.scaler.grievancemanagementservice.dtos.GrievanceRequestDto;
import com.scaler.grievancemanagementservice.dtos.GrievanceResponseDto;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/grievances")
public interface GrievanceOperations {

    @GetMapping("/")
    List<GrievanceResponseDto> getAllGrievances();

    @GetMapping("/{id}")
    GrievanceResponseDto getGrievanceById(@PathVariable Long id);

    @PostMapping("/{id}")
     void createGrievance(@RequestBody GrievanceRequestDto grievanceRequestDto);

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id);

    @PutMapping("/{id}")
    void update(@RequestBody GrievanceRequestDto grievanceRequestDto);


}
