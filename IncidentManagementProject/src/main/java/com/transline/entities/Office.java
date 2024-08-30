package com.transline.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Office {
	
	@ManyToOne
	@JoinColumn(name = "cmp_cd", nullable = false)
	private CompanyMst companyMst;	
	
	@Id
	private String offCd;

	@Column(nullable = false)
	private String offName;

	@Column(nullable = false)
	private String offType;

	@Column(nullable = false)
	private String offAddress;

	@Column(nullable = false)
	@Pattern(regexp = "^[A-Z0-9]+$", message = "Invalid control code format")
	private String ctlCd;

	@Email(message = "Invalid email format")
	private String email;

	@Pattern(regexp = "^\\+?[0-9]*$", message = "Invalid phone number format")
	private String phoneNo;

	private String contactPerson;

	@Pattern(regexp = "^(TRUE|FALSE|NULL)$", message = "Invalid disable status format")
	private String disable;

	@Pattern(regexp = "^(RO|WO|RWO)$", message = "Invalid working status format")
	@Column(nullable = false)
	private String workingStatus;

}
