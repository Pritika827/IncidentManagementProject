package com.transline.dtos;

import lombok.Data;

@Data
public class InsuranceDto {

	private Integer id;
	private String incidentId; // This will be a String for DTO
	private String busInsuranceCompanyName;
	private String busPolicyNo;
	private OtherVehicleDTO otherVehicle;
	private String busLossDescription;
	private String busOwner;
	private String busAddress;
	private Boolean isAnimalInvolvement;
	private Double thirdPartyCompensation;

	@Data
	public static class OtherVehicleDTO {
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
