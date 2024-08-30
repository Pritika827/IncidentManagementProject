package com.transline.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyMstDto {

  //  private Integer id;
    private String cmpCd;
    private String cmpName;
    private String cmpAdd;
    private String city;
    private String state;
    private String website;
    private String cmpLogo;
    private String email;
    private String phn1;
    private String phn2;
    private String status;
    private LocalDateTime createdAt = LocalDateTime.now();
}
