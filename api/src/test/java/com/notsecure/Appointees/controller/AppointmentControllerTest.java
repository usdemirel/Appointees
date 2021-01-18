package com.notsecure.Appointees.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentControllerTest {
   
   @InjectMocks
   AppointmentController appointmentController;

@BeforeEach
void setUp() {
   MockitoAnnotations.initMocks(this);
}

@Test
void findById() {
}

@Test
void save() {
}
}