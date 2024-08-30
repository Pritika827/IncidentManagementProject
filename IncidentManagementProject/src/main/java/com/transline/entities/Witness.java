package com.transline.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "witnesses")
@Data
public class Witness {

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

	@ElementCollection
	private List<OtherReference> anyOtherRef; // Stores a list of names and addresses

	private String description;
	private LocalDate date;
	private LocalTime time;

	@Data
	@Embeddable
	public static class OtherReference {
		private String refName;
		private String refAddress;
	}
}
