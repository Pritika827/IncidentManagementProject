package com.transline.dtos;

import com.transline.enums.StaffType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StaffsDto {

	private Integer staffId;

	private String name;
	private String licenseNumber;
	private String vehicleNo;
	private int age;
	private String bloodGroup;
	private StaffType staffType;

}
