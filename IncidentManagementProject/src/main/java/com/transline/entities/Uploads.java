package com.transline.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Uploads")
@Data
public class Uploads {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uploadId;
	private String uploadType;

	private String refId;// (table PK)
	private String filePath;
	private String fileDescription;
	private String fileSize;

	private String createdBy;
	private LocalDateTime createdAt;

	@PrePersist
	protected void onCreate() {
		this.createdBy = "system"; // Default value for createdBy
		this.createdAt = LocalDateTime.now(); // Set current time for createdAt
	}
}