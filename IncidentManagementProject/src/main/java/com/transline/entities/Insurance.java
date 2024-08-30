package com.transline.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "insurance")
@Data
public class Insurance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne
	@JoinColumn(name = "incident_id", nullable = false)
	private Incidents incident;
	private String busInsuranceCompanyName;
	private String busPolicyNo;

	@Embedded
	private OtherVehicleDetails otherVehicle;

	private String busLossDescription;
	private String busOwner;
	private String busAddress;
	private Boolean isAnimalInvolvement;
	private Double thirdPartyCompensation;

	@Data
	@Embeddable
	public static class OtherVehicleDetails {

		private String vehicleNo;
		private String manufactureName;
		private String lossDescription;
		private String ownerName;
		private String ownerAddress;
		private String driverName;
		private String driverAddress;
		private String insuranceCompanyName;
		private String insuranceAddress;
	}
}
