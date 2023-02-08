package com.makaia.hospital;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.makaia.hospital.Controller.AppointmentController;
import com.makaia.hospital.DTO.ResponseSchedule;
import com.makaia.hospital.Entity.Appointment;
import com.makaia.hospital.Enum.UserType;
import com.makaia.hospital.Exception.ErrorResponse;
import com.makaia.hospital.Service.AppointmentService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class HospitalApplicationTests {

    @Mock
    private AppointmentService appointmentService;
    @InjectMocks
    private AppointmentController appointmentController;

    @Test
    public void scheduleAppointment_ShouldReturnResponseEntityWithCreatedStatusAndResponseScheduleObject_WhenUserDoesNotHaveExistingAppointment() {
        Appointment appointment = new Appointment();
        appointment.setUserId("123");
        appointment.setUserType(UserType.PARTICULAR);
        ResponseSchedule responseSchedule = new ResponseSchedule(456L, LocalDate.of(2022, 1, 1));
        when(appointmentService.scheduleAppointment(appointment)).thenReturn(new ResponseEntity<>(responseSchedule, HttpStatus.CREATED));

        ResponseEntity<Object> response = appointmentController.scheduleAppointment(appointment);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseSchedule, response.getBody());
    }

    @Test
    public void scheduleAppointment_ShouldReturnResponseEntityWithBadRequestStatusAndErrorResponseObject_WhenUserHasExistingAppointment() {
        Appointment appointment = new Appointment();
        appointment.setUserId("123");
        appointment.setUserType(UserType.PARTICULAR);
        ErrorResponse errorResponse = new ErrorResponse("El usuario con identificación 123 ya tiene una cita agendada, por lo cual no podrá realizar más agendamientos.");
        when(appointmentService.scheduleAppointment(appointment)).thenReturn(new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST));

        ResponseEntity<Object> response = appointmentController.scheduleAppointment(appointment);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(errorResponse, response.getBody());
    }

    @Test
    public void dateScheduleAppointment_ShouldReturnResponseEntityWithCreatedStatusAndAppointmentObject_WhenAppointmentExists() {
        Appointment appointment = new Appointment();
        appointment.setId(123L);
        appointment.setUserId("456");
        appointment.setUserType(UserType.EPS);
        appointment.setDateAppointment(LocalDate.of(2022, 1, 1));
        when(appointmentService.dateScheduleAppointment(123L)).thenReturn(new ResponseEntity<>(appointment, HttpStatus.CREATED));

        ResponseEntity<Object> response = appointmentController.dateScheduleAppointment(123L);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(appointment, response.getBody());
    }
}
