package com.scaler.adminmanagementservice.controllers;

import com.scaler.adminmanagementservice.services.AdminService;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
class AdminOperationsTest {

    @MockBean
    private AdminService adminService;

}