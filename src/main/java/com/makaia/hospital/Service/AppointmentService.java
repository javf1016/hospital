package com.makaia.hospital.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.makaia.hospital.Entity.Appointment;
import com.makaia.hospital.Enum.UserType;
import com.makaia.hospital.Repository.AppointmentRepository;

@Service
public class AppointmentService {

	@Autowired
    private AppointmentRepository appointmentRepository;

    public ResponseEntity<Object> scheduleAppointment(Appointment appointment) {
        Appointment existAppointment = appointmentRepository.findByIdentificacionUsuarioAndTipoUsuario(appointment.getUserId(), UserType.PARTICULAR);

        if (existAppointment != null) {
            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "El usuario con identificación " + appointment.getUserId() + " ya tiene una cita agendada, por lo cual no podrá realizar más agendamientos.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Appointment saveAppointment = appointmentRepository.save(appointment);
        return new ResponseEntity<>(saveAppointment, HttpStatus.CREATED);
    }
}
