package com.scaler.grievancemanagementservice.services;

import com.scaler.grievancemanagementservice.dtos.GrievanceRequestDto;
import com.scaler.grievancemanagementservice.dtos.GrievanceResponseDto;
import com.scaler.grievancemanagementservice.models.Grievance;
import com.scaler.grievancemanagementservice.models.GrievanceStatus;
import com.scaler.grievancemanagementservice.models.User;
import com.scaler.grievancemanagementservice.repositories.GrievanceRepository;
import com.scaler.grievancemanagementservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GrievanceServiceImpl implements GrievanceService{

    private final GrievanceRepository grievanceRepository;
    private final UserRepository userRepository;

    @Override
    public GrievanceResponseDto createGrievance(GrievanceRequestDto grievanceRequestDto) {
        Grievance grievance = Grievance.builder()
                .title(grievanceRequestDto.getGrievanceTitle())
                .description(grievanceRequestDto.getGrievanceDescription())
                .status(GrievanceStatus.PENDING)
                .build();
        Grievance savedGrievance = grievanceRepository.save(grievance);
        return convertGrievanceToGrievanceResponseDto(savedGrievance);
    }

    @Override
    public void updateGrievance(GrievanceRequestDto grievanceRequestDto) {
        Optional<Grievance> grievanceOptional = grievanceRepository.findById(grievanceRequestDto.getGrievanceId());
        if(grievanceOptional.isPresent()){
            Grievance grievance = grievanceOptional.get();
            grievance.setTitle(grievanceRequestDto.getGrievanceTitle());
            grievance.setDescription(grievanceRequestDto.getGrievanceDescription());
            grievanceRepository.save(grievance);
        }
    }

    @Override
    public void deleteGrievance(Long id) {
        grievanceRepository.deleteById(id);
    }

    @Override
    public GrievanceResponseDto getGrievanceById(Long id) {
        Optional<Grievance> grievance = grievanceRepository.findById(id);
        return grievance.map(this::convertGrievanceToGrievanceResponseDto).orElse(null);
    }
    @Override
    public List<GrievanceResponseDto> getAllGrievances() {
        List<Grievance> grievances = grievanceRepository.findAll();
        return grievances.stream().map(this::convertGrievanceToGrievanceResponseDto).collect(Collectors.toList());
    }

    @Override
    public List<GrievanceResponseDto> getAllGrievancesByUserId(Long id) {
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()){
            return null;
        }

        List<Grievance> grievances = grievanceRepository.findAllByCreatedBy(user.get());
        return grievances.stream().map(this::convertGrievanceToGrievanceResponseDto).collect(Collectors.toList());
    }

    public GrievanceResponseDto convertGrievanceToGrievanceResponseDto(Grievance grievance){
        return GrievanceResponseDto.builder()
                .grievanceId(grievance.getId())
                .grievanceDescription(grievance.getDescription())
                .grievanceTitle(grievance.getTitle())
                .grievanceStatus(grievance.getStatus().toString())
                .build();
    }
}
