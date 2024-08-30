package com.transline.entities;

import com.transline.enums.StaffType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Staffs")
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Staffs {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer staffId;
	private String name;
	private String licenseNumber;
	private String vehicleNo;
	private int age;
	private String bloodGroup;

	//@Enumerated(EnumType.STRING)
	private StaffType staffType;

}
