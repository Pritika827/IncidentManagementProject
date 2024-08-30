package com.transline.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class WitnessAndOtherDto {

    private Integer id;

    private String incidentId; // Assuming you want to use the ID directly for simplicity

    private String name;
    private String address;
    private Boolean isInBus;
    private String seatDirection; // Applicable if isInBus is true
    private Boolean seenIncident;
    private Integer busSpeed;
    private Boolean isHorned;
    private Boolean isBeaked;
    private Boolean tryToStopIncident;
    private String culprit;
    private String description;
    private LocalDate date;
    private LocalTime time;

    @Pattern(regexp = "^[OW]$", message = "witnessType must be either 'O' or 'W'")
    private String witnessType; // 'O' for Original, 'W' for Witness

}
