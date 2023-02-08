package com.makaia.hospital.Entity;

import javax.persistence.*;

import com.makaia.hospital.Enum.UserType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "appointments")
@ApiModel(description = "All details about the Appointments. ")
public class Appointment {
	
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @ApiModelProperty(notes = "The database generated Appointment ID")
   private Long id;
   
   @Column(name = "specialty", length = 10)
   @ApiModelProperty(notes = "The specialty of appoinment")
   private String specialty;
   
   @Column(name = "user_id", length = 10)
   @ApiModelProperty(notes = "The Id of the User")
   private String userId;
   
   @Enumerated(EnumType.STRING)
   @Column(name = "user_type")
   @ApiModelProperty(notes = "The type of the User")
   private UserType userType;
   
   public Appointment() {}
   
   public Appointment(String specialty, String userId, UserType userType) {
      this.specialty = specialty;
      this.userId = userId;
      this.userType = userType;
   }
   
   public Long getId() {
      return id;
   }
   
   public void setId(Long id) {
      this.id = id;
   }
   
   public String getSpecialty() {
      return specialty;
   }
   
   public void setSpecialty(String specialty) {
      this.specialty = specialty;
   }
   
   public String getUserId() {
      return userId;
   }
   
   public void setUserId(String userId) {
      if (userId.length() <= 10) {
         this.userId = userId;
      } else {
         throw new IllegalArgumentException("El número de identificación del usuario debe tener un máximo de 10 dígitos.");
      }
   }
   
   public UserType getUserType() {
      return userType;
   }
   
   public void setUserType(UserType userType) {
      this.userType = userType;
   }
}


