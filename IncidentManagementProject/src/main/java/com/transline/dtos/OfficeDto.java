package com.transline.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfficeDto {

    private String offCd; 

    private String offName; 

    private String offType; 

    private String offAddress; 

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
    private String workingStatus; 

    private String cmpCd; // Company Code (Foreign key reference to CompanyMst)

}
