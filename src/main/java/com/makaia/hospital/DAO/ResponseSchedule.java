package com.makaia.hospital.DAO;

import java.time.LocalDate;

public class ResponseSchedule {

	private Long id;
	private LocalDate dateAppointment;
	
	public ResponseSchedule(Long id, LocalDate dateAppointment) {
	  this.id = id;
	  this.dateAppointment = dateAppointment;
	}
	
	public Long getId() {
	  return id;
	}

	public LocalDate getDateAppointment() {
		return dateAppointment;
	}

}