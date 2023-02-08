package com.makaia.hospital.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.makaia.hospital.Entity.Appointment;
import com.makaia.hospital.Service.AppointmentService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/schedule")
@Api(value = "Appointment Management System", description = "Operations related to appointments")
public class AppointmentController {

	@Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<Object> agendarCitaMedica(@RequestBody Appointment appointment) {
        return appointmentService.scheduleAppointment(appointment);
    }
}
