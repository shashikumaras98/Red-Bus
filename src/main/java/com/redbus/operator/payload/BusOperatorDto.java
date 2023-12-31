package com.redbus.operator.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.redbus.operator.entity.TicketCost;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BusOperatorDto {

    private String busId; // we follow id --> as we did in Microservices

    private String busNumber;

    private String busOperatorCompanyName;

    private String driverName;

    private String supportStaff;

    private int numberOfSeats;

    private String departureCity;

    private String arrivalCity;

    //to give time --> do re-search (chatGPT)
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime departureTime;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime arrivalTime;


    @JsonFormat(pattern = "dd/MM/yyyy") // to change Date format (it will convert String(Json obj) to Date type to save in Database)
    @Temporal(TemporalType.TIMESTAMP)  // to store only date in Database
    private Date departureDate;

   @JsonFormat(pattern = "dd/MM/yyyy")  // to change Date format (it will convert String(Json obj) to Date type to save in Database)
   @Temporal(TemporalType.TIMESTAMP) // to store only date in Database
    private Date arrivalDate;

    private double totalTravelTime;

    private String busType;

    private  String amenities;

    private TicketCost ticketCost; // this variable name should be same that we give in JSON object
}
