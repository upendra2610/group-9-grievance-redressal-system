package com.scaler.grievancemanagementservice.services;


import com.scaler.grievancemanagementservice.dtos.GrievanceRequestDto;
import com.scaler.grievancemanagementservice.dtos.GrievanceResponseDto;

import java.util.List;

public interface GrievanceService {

     GrievanceResponseDto createGrievance(GrievanceRequestDto grievanceRequestDto);
     void updateGrievance(GrievanceRequestDto grievanceRequestDto);
     void deleteGrievance(Long id);
     GrievanceResponseDto getGrievanceById(Long id);
     List<GrievanceResponseDto> getAllGrievances();

     List<GrievanceResponseDto> getAllGrievancesByUserId(Long id);


}
