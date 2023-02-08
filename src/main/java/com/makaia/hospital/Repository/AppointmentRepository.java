package com.makaia.hospital.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.makaia.hospital.Entity.Appointment;
import com.makaia.hospital.Enum.UserType;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	
	 @Query("SELECT a FROM Appointment a WHERE a.userId = :userId AND a.userType = :userType")
	 Appointment findByIdentificacionUsuarioAndTipoUsuario(@Param("userId") String userId, @Param("userType") UserType userType);
	 
}
