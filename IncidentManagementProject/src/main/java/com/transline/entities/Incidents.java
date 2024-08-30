package com.transline.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.transline.enums.AccidentType;
import com.transline.enums.AreaType;
import com.transline.enums.Region;
import com.transline.enums.Zone;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "incidents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Incidents {

	@Id
//	@Column(name = "incident_id", nullable = false, unique = true)
//	@GeneratedValue(generator = "incident-id-generator")
//    @GenericGenerator(name = "incident-id-generator", strategy = "com.transline.IncidentIdGenerator2")
//	@GeneratedIncidentId	
	private String incidentId; // Primary Key // DTC202400001

	@OneToMany(mappedBy = "incident", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FurtherRemarks> furtherRemarks = new ArrayList<>();
	
	@ManyToOne
    @JoinColumn(name = "cmp_cd")
    private CompanyMst companyMst;
	
	
	@Column(name = "rsc_dairy_no")
	private String rscDairyNo;

	@Column(name = "informed_by")
	private String informedBy;

	@Column(name = "attended_by")
	private String attendedBy;

	@Column(name = "bus_id")
	private String busId;

	@Column(name = "accident_type")
	private AccidentType accidentType;

	@Column(name = "depot_id")
	private String depotId;

	@Column(name = "no_of_passenger")
	private Integer noOfPassenger;

	@Column(name = "driver_id")
	private String driverId; // Staff ID

	@Column(name = "conductor_id")
	private String conductorId; // Staff ID

	@Column(name = "bus_damage")
	private boolean busDamage; // true/false

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "time")
	private LocalTime time;

	@Column(name = "route_no")
	private String routeNo;

	@Column(name = "duty_no")
	private String dutyNo;

	@Column(name = "location_name")
	private String locationName; // Accident location

	@Column(name = "city")
	private String city; // Accident city

	@Column(name = "pincode")
	private String pincode; // Accident pincode

	@Enumerated(EnumType.STRING)
	@Column(name = "zone")
	private Zone zone;

	@Enumerated(EnumType.STRING)
	@Column(name = "area_type")
	private AreaType areaType;

	@Enumerated(EnumType.STRING)
	@Column(name = "region")
	private Region region;

	@Column(name = "damaged_description")
	private String damagedDescription;

	@Column(name = "injured")
	private Integer injured;

	@Column(name = "death")
	private Integer death;

	
//	@PrePersist
//	private void prePersist() {
//		if (this.incidentId == null) {
//			IncidentIdGenerator incidentIdGenerator=new IncidentIdGenerator();
//			this.incidentId = incidentIdGenerator.generateIncidentId();
//		}
//	}


	public Incidents(String incidentId) {
		super();
		this.incidentId = incidentId;
	}
	
	
}
