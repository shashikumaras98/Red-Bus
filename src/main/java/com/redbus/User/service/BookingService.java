package com.redbus.User.service;

import com.redbus.User.entity.Booking;
import com.redbus.User.payload.BookingDetailsDto;
import com.redbus.User.payload.PassengerDetails;
import com.redbus.User.repository.BookingRepository;
import com.redbus.operator.entity.BusOperator;
import com.redbus.operator.entity.TicketCost;
import com.redbus.operator.repository.BusOperatorRepository;
import com.redbus.operator.repository.TicketCostRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.UUID;

@Service
public class BookingService {

    private BusOperatorRepository busOperatorRepo;
    private TicketCostRepository ticketCostRepo;
    private BookingRepository bookingRepo;

    public BookingService(BusOperatorRepository busOperatorRepo, TicketCostRepository ticketCostRepo, BookingRepository bookingRepo) {
        this.busOperatorRepo = busOperatorRepo;
        this.ticketCostRepo = ticketCostRepo;
        this.bookingRepo = bookingRepo;
    }

    public BookingDetailsDto createBooking(String  busId,
                                           String  ticketId ,
                                           PassengerDetails passengerDetails
    ){

        //based on the busId --> get Bus details

        BusOperator bus = busOperatorRepo.findById(busId).get(); //  --> it will consist of Bus details

        //mistake --> search the ticketCost by using busId  --> it should return --> TicketCost object  --> add one method  TicketCostRepository (findBYBusId)- in Repository layer
        //String ticketId1 = bus.getTicketCost().getTicketId();

        TicketCost ticketCost = ticketCostRepo.findById(ticketId).get();

        ///////////////////////////////////    Payment    ///////////////////////////////////////////////////////

        //before booking the ticket --> make the Payment (call the method & pass the Ticket cost)

        String paymentIntent = createPaymentIntent((int) ticketCost.getCost());

        if(paymentIntent!=null) {


            Booking booking = new Booking();

            String bookingId = UUID.randomUUID().toString();

            booking.setBookingId(bookingId);
            booking.setBusId(busId);
            booking.setTicketId(ticketId);
            booking.setFromCity(bus.getDepartureCity());
            booking.setToCity(bus.getArrivalCity());
            booking.setBusCompany(bus.getBusOperatorCompanyName());
            booking.setPrice(ticketCost.getCost());
            booking.setFirstName(passengerDetails.getFirstName());
            booking.setLastName(passengerDetails.getLastName());
            booking.setEmail(passengerDetails.getEmail());
            booking.setMobile(passengerDetails.getMobile());


            Booking ticketCreatedDetails = bookingRepo.save(booking); // entity


            //entity to --> dto
            BookingDetailsDto dto = new BookingDetailsDto();

            dto.setBookingId(ticketCreatedDetails.getBookingId());
            dto.setBusCompany(ticketCreatedDetails.getBusCompany());
            dto.setFrom(ticketCreatedDetails.getFromCity());
            dto.setTo(ticketCreatedDetails.getToCity());
            dto.setFirstName(ticketCreatedDetails.getFirstName());
            dto.setLastName(ticketCreatedDetails.getLastName());
            dto.setEmail(ticketCreatedDetails.getEmail());
            dto.setMobile(ticketCreatedDetails.getMobile());
            dto.setPrice(ticketCreatedDetails.getPrice());
            // one more thing in BookingDetails Dto to shoe the message that Booking is confirmed
            dto.setMessage("----Booking Confirmed----");

            return dto;

        }
        else {
            System.out.println("Error in Booking the ticket");
        }
        return null;
    }

    //Payment Gateway
    @Value("${stripe.api.key}")
    private String stripeApiKey;

    public String createPaymentIntent(Integer amount) {
        // we will not supply amount via API , but we will call the method & supply the value(amount)

        Stripe.apiKey = stripeApiKey;

        try {

            // Create a charge
            PaymentIntent intent = PaymentIntent.create(
                    new PaymentIntentCreateParams.Builder()
                            .setCurrency("usd")
                            .setAmount((long) amount * 100 ) // amount in cents
                            .build()
            );

            // Handle success
            return generateResponse("Payment successful. Charge ID: " + intent.getClientSecret());
        } catch (StripeException e) {
            // Handle failure
            return generateResponse("Payment failed. Error: " + e.getMessage());
        }
    }

    private String generateResponse(String clientSecret) {
        return "{\"clientSecret\":\"" + clientSecret + "\"}";
    }

}
