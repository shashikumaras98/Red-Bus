package com.redbus.operator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "ticket_cost")
public class TicketCost {

    @Id
    @Column(name = "ticket_id")
    private String ticketId;

    @Column(name = "bus_id" , unique = true)
    private String busId;

    @Column(name = "cost")
    private double cost;

    @Column(name = "code")
    private String code; // by applying this Coupon code --> we get the discount on Cost

    @Column(name = "discount_amount")
    private double discountAmount;

    @OneToOne(mappedBy = "ticketCost")
    @JoinColumn(name = "bus_id") //refer to the column in the BusOperator table
    private BusOperator busOperator;

}
