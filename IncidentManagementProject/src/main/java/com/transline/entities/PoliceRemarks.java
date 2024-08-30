package com.transline.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "police_remarks")
@Data
public class PoliceRemarks {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

//	@Column(nullable = false)
//	private String incidentId;
	
	@OneToOne
	@JoinColumn(name = "incident_id", nullable = false)
	private Incidents incident;

	private String name;
	private String location;
	private String policeStationName;
	private Boolean isIncidentSeen;
	private String description;

	@Embedded
	private InvestigationReport investigationReport;

	private String drName;
	private String drAddress;
	private String hospitalName;
	private String referenceNo;

	@PrePersist
	@PreUpdate
	private void prePersistOrUpdate() {
		
	}

	@PostLoad
	private void postLoad() {
		
	}

	@Data
	@Embeddable
	public static class InvestigationReport {
		private String reportName;
		private String address;
		private String injuredDescription;
		private String remarks;

	
	}
}
