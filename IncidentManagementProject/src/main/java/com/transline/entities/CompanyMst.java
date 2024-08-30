package com.transline.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "cmp_mst")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyMst {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;
		
	@Id
	private String cmpCd;

	@Column(nullable = false)
	private String cmpName;

//	@OneToMany(mappedBy = "office", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Office> offices = new ArrayList<>();
	

	private String cmpAdd;
	private String city;
	private String state;
	private String website;

	@Column(length = 255) 
	private String cmpLogo;

	private String email;
	private String phn1;
	private String phn2;

	private String status;

	private LocalDateTime createdAt;
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now(); 
	}
		
//	public CompanyMst(String cmpCd) {
//		super();
//		this.cmpCd = cmpCd;
//	}
}
