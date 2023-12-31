package com.redbus.operator.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.redbus.operator.util.CustomDataDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;



@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "bus_Operators")
public class BusOperator {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)  // only long/int type is allowed for auto-increment
    @Column(name = "bus_Id")
    private String busId; // we follow id --> as we did in Microservices

    @Column(name = "bus_number")
    private String busNumber;

    @Column(name = "bus_operator_company_name")
    private String busOperatorCompanyName;


    @Column(name = "driver_name")
    private String driverName;

    @Column(name = "support_staff")
    private String supportStaff;

    @Column(name = "number_of_seats")
    private int numberOfSeats;

    @Column(name = "departure_city")
    private String departureCity;

    @Column(name = "arrival_city")
    private String arrivalCity;

    @Column(name = "departure_time")
    private LocalTime departureTime;

    @Column(name = "arrival_time")
    private LocalTime arrivalTime;


    @Column(name = "departure_Date")
    @JsonDeserialize(using = CustomDataDeserializer.class)
    @Temporal(TemporalType.DATE) //--> To store only date in Database
    private Date departureDate;

    @Column(name = "arrival_date")
    @JsonDeserialize(using = CustomDataDeserializer.class)
    @Temporal(TemporalType.DATE)  //--> to store only date in Database
    private Date arrivalDate;

    @Column(name = "total_travel_time")
    private double totalTravelTime;

    @Column(name = "bus_type")
    private String busType;

    @Column(name = "amenities")
    private  String amenities;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bus_Id") //refer to the column name in the TicketCost table
    private TicketCost ticketCost;

}
