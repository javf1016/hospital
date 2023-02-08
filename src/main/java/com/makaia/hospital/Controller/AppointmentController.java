package com.makaia.hospital.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.makaia.hospital.Entity.Appointment;
import com.makaia.hospital.Exception.ErrorResponse;
import com.makaia.hospital.Service.AppointmentService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/agendar")
@Api(value = "Appointment Management System", description = "Operations related to appointments")
public class AppointmentController {

	@Autowired
    private AppointmentService appointmentService;

	@PostMapping
	public ResponseEntity<Object> scheduleAppointment(@Validated @RequestBody Appointment appointment) {
		try {
			return appointmentService.scheduleAppointment(appointment);
		} catch (HttpMessageNotReadableException e) {
			if (e.getCause() instanceof InvalidFormatException) {
				return new ResponseEntity<>(new ErrorResponse("Tipo de usuario no permitido en el hospital"), HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(new ErrorResponse("Error desconocido"), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> dateScheduleAppointment(@PathVariable Long id) {
		return appointmentService.dateScheduleAppointment(id);
	}
    
}
