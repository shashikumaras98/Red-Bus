package com.redbus.User.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookingDetailsDto {

    private String bookingId;

    //busId & ticketId --> will come from URL

    //remaining details we will give it in JSON

    private String busCompany;
    private String from;
    private String to;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private double price;

    private String message;

}
