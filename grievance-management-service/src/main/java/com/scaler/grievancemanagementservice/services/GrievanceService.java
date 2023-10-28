package com.scaler.grievancemanagementservice.services;


import com.scaler.grievancemanagementservice.dtos.GrievanceRequestDto;
import com.scaler.grievancemanagementservice.dtos.GrievanceResponseDto;

import java.util.List;

public interface GrievanceService {

    public GrievanceResponseDto createGrievance(GrievanceRequestDto grievanceRequestDto);
    public void updateGrievance(GrievanceRequestDto grievanceRequestDto);
    public void deleteGrievance(Long id);
    public GrievanceResponseDto getGrievanceById(Long id);
    public List<GrievanceResponseDto> getAllGrievances();

    public List<GrievanceResponseDto> getAllGrievancesByUserId(Long id);


}
