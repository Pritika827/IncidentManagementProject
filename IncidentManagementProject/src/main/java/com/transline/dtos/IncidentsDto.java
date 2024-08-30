package com.transline.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.transline.enums.AccidentType;
import com.transline.enums.AreaType;
import com.transline.enums.Region;
import com.transline.enums.Zone;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IncidentsDto {

	private String incidentId;

	private String cmpCd;
	
	private String rscDairyNo;

//	@NotBlank(message = "Informed by is mandatory")
	private String informedBy;

	private String attendedBy;

//	@NotBlank(message = "Bus ID is mandatory")
	private String busId;

	private AccidentType accidentType;

//	@NotBlank(message = "Depot ID is mandatory")
	private String depotId;

	@Min(value = 0, message = "Number of passengers must be zero or more")
	private Integer noOfPassenger;

//	@NotBlank(message = "Driver ID is mandatory")
	private String driverId; // Staff ID

	private String conductorId; // Staff ID

	private boolean busDamage;

//	@NotNull(message = "Date is mandatory")
	private LocalDate date;

//	@NotNull(message = "Time is mandatory")
	private LocalTime time;

	private String routeNo;

	private String dutyNo;

	private String locationName; // Accident location

	private String city; // Accident city

	@Pattern(regexp = "\\d{6}", message = "Pincode must be a 6-digit number")
	private String pincode; // Accident pincode

//	@NotNull(message = "Zone is mandatory")
	private Zone zone;

//	@NotNull(message = "Area type is mandatory")
	private AreaType areaType;

//	@NotNull(message = "Region is mandatory")
	private Region region;

	private String damagedDescription;

	@Min(value = 0, message = "Injured count must be zero or more")
	private Integer injured;

	@Min(value = 0, message = "Death count must be zero or more")
	private Integer death;

}
