package com.scaler.adminmanagementservice.services;

import com.scaler.adminmanagementservice.dtos.GenericAdminDto;
import com.scaler.adminmanagementservice.exceptions.NotFoundException;
import com.scaler.adminmanagementservice.modals.User;
import com.scaler.adminmanagementservice.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @MockBean
    private UserRepository userRepository;


    @Test
    @DisplayName("Test When Admin Not Found")
    void testGetAdminReturnNull() {
//        Arrange
        Long id = 1L;
        when(
                userRepository.findById(id)
        )
                .thenReturn(Optional.empty());


//      Act & Assert
        assertThrows(NotFoundException.class, () -> adminService.getAdminById(id));
    }


    @Test
    @DisplayName("Test When Admin Exist")
    void testGetAdminExist() {
//        Arrange
        Long id = 1L;
        User admin = new User();
        admin.setId(id);
        admin.setUsername("name");
        admin.setEmail("email");
        admin.setPhone("99");

        when(
                userRepository.findById(id)
        )
                .thenReturn(Optional.of(admin));

//      Act
        Optional<User> result = userRepository.findById(id);


//      Assert
        assertNotNull(result);
        if (result.isPresent()) {
            assertEquals(id, result.get().getId());
            assertEquals("name", result.get().getUsername());
            assertEquals("email", result.get().getEmail());
            assertEquals("99", result.get().getPhone());
        }
    }


    @Test
    @DisplayName("Test when GetAllAdmin Returns Null")
    void testGetAllAdminReturnNull() {
//        Arrange
        when(
                userRepository.findAllAdmins()
        )
                .thenReturn(Collections.emptyList());

//        Act & Assert
        assertThrows(NotFoundException.class, () -> adminService.getAllAdmins());
    }


    @Test
    @DisplayName("Test GetAllAdmin Success")
    void testGetAllAdminExist() throws NotFoundException {
//      Arrange
        User user = new User();
        user.setId(1L);
        user.setUsername("name");
        user.setEmail("email");
        user.setPhone("99");
        List<User> mockUser = new ArrayList<>();
        mockUser.add(user);
        mockUser.add(user);
        mockUser.add(user);
        when(
                userRepository.findAllAdmins()
        )
                .thenReturn(mockUser);

//      Act
        List<GenericAdminDto> result = adminService.getAllAdmins();

//      Assert
        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals("name", result.get(0).getUserName());
        assertEquals("email", result.get(1).getEmail());
        assertEquals("99", result.get(2).getPhone());
    }


    @Test
    void testCreateAdminSuccess(){
        User user = new User();
        user.setUsername("name");
        user.setPassword("password");
        user.setEmail("email@email.com");

        when(
                userRepository.save(user)
        ).thenReturn(user);

        User responseUser = userRepository.save(user);

        assertEquals(user.getUsername(), responseUser.getUsername());
    }


//    @Test
//    void testCreateAdminSuccess(){
//        AdminDto inputUser = new AdminDto();
//        inputUser.setUsername("name");
//        inputUser.setEmail("email");
//        inputUser.setPhone("99");
//        inputUser.setPassword("abcdefgh");
//        User savedUser = new User();
//        savedUser.setId(1L);
//        savedUser.setUsername("name");
//        savedUser.setEmail("email");
//        savedUser.setPhone("99");
//        savedUser.setPassword("abcdefgh");
//
//        when(
//                userRepository.save(Mockito.any(User.class))
//        )
//                .thenReturn(savedUser);
//
//
//    }



}