package com.transline.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name = "witnessesAndOther")
@Data
public class WitnessAndOther {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne
	@JoinColumn(name = "incident_id", nullable = false)
	private Incidents incident;

	private String name;
	private String address;
	private Boolean isInBus;
	private String seatDirection; // Applicable if isInBus is true
	private Boolean seenIncident;
	private Integer busSpeed;
	private Boolean isHorned;
	private Boolean isBeaked;
	private Boolean tryToStopIncident;
	private String culprit;
	private String description;
	private LocalDate date;
	private LocalTime time;

	@Pattern(regexp = "^[OW]$", message = "witnessType must be either 'O' or 'W'")
    private String witnessType; // 'O' for Original, 'W' for Witness

}
