package com.scaler.grievancemanagementservice.controllers;

import com.scaler.grievancemanagementservice.dtos.GrievanceRequestDto;
import com.scaler.grievancemanagementservice.dtos.GrievanceResponseDto;
import com.scaler.grievancemanagementservice.services.GrievanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/grievances")
@RequiredArgsConstructor
public class GrievanceController implements GrievanceOperations{

    public final GrievanceService grievanceService;

    @Override
    public List<GrievanceResponseDto> getAllGrievances() {
        return grievanceService.getAllGrievances();
    }

    @Override
    public GrievanceResponseDto getGrievanceById(Long id) {
        return grievanceService.getGrievanceById(id);
    }

    @Override
    public void createGrievance(GrievanceRequestDto grievance) {
         grievanceService.createGrievance(grievance);
    }

    @Override
    public void deleteById(Long id) {
        grievanceService.deleteGrievance(id);
    }

    @Override
    public void update(GrievanceRequestDto grievanceRequestDto) {
         grievanceService.updateGrievance(grievanceRequestDto);
    }
}
