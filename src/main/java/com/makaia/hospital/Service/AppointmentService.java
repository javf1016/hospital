package com.makaia.hospital.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.makaia.hospital.Entity.Appointment;
import com.makaia.hospital.Repository.AppointmentRepository;

@Service
public class AppointmentService {

	@Autowired
    private AppointmentRepository appointmentRepository;

    public ResponseEntity<Object> scheduleAppointment(Appointment appointment) {
    	Appointment existAppointment = appointmentRepository.findByIdentificacionUsuarioAndTipoUsuario(appointment.getUserId(), appointment.getUserType());

    	if (existAppointment != null) {
    		Map<String, String> response = new HashMap<>();
    		response.put("mensaje", "El usuario con identificación " + appointment.getUserId() + " ya tiene una cita agendada, por lo cual no podrá realizar más agendamientos.");
    		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    	}
        
        calculateDateAppointment(appointment);
        Appointment saveAppointment = appointmentRepository.save(appointment);
        return new ResponseEntity<>(saveAppointment, HttpStatus.CREATED);
    }
    
    public Appointment calculateDateAppointment(Appointment appointment) {
        switch (appointment.getUserType()) {
          	case EPS:
          		appointment.setDateAppointment(calculateDate(10, false));
            break;
          	case POLIZA:
          		appointment.setDateAppointment(calculateDate(8, true));
            break;
          	case PARTICULAR:
          		appointment.setDateAppointment(calculateDate(7, false));
            break;
          	default:
                Map<String, String> response = new HashMap<>();
                response.put("mensaje", "Tipo de usuario no permitido en el hospital");
                new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return appointment;
    }

    private LocalDate calculateDate(int days, boolean countWeekend) {
    	LocalDate dateNow = LocalDate.now();
        while (days > 0) {
        	dateNow = dateNow.plusDays(1);
        	if (countWeekend || dateNow.getDayOfWeek().getValue() < 6) {
        		days--;
        	}
        }
        return dateNow;
    }
    
}

