package com.transline.enums;

public enum AnalysisOfAccident {
    RASH_DRIVING("Rash Driving"),
    NEGLIGENT_DRIVING("Negligent Driving"),
    ERROR_OF_JUDGEMENT("Error of Judgement"),
    MECHANICAL_DTC_DEFECT("Mechanical (DTC Defect)"),
    LACK_OF_ROAD_SENSE("Lack of Road Sense"),
    ALIGHTING_PASSENGER("Alighting Passenger"),
    BOARDING_PASSENGER("Boarding Passenger"),
    CONTRIBUTING("Contributing"),
    MISCELLANEOUS("Miscellaneous");

    private final String description;

    AnalysisOfAccident(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}