package com.redbus.User.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "bookings")
public class Booking { //--> it has Bus details , Passenger details , Ticket details

    //so the information has to come from 2 tables --> BusOperator & TicketCost tables

    @Id
    @Column(name = "booking_Id")
    private String bookingId;

    @Column(name = "bus_id")
    private String busId;

    @Column(name = "ticket_id")
    private String ticketId;

    @Column(name = "bus_Company")
    private String busCompany;

    @Column(name = "from_city")
    private String fromCity;

    @Column(name = "to_city")
    private String toCity;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "price")
    private double price;

    //also add departureDate , departureTime , bustype ,amenities..........

}
