package com.redbus.User.controller;


import com.redbus.User.payload.BookingDetailsDto;
import com.redbus.User.payload.PassengerDetails;
import com.redbus.User.service.BookingService;
import com.redbus.util.EmailService;
import com.redbus.util.PdfService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private BookingService bookingService;
    private EmailService emailService;
    private PdfService pdfService;

    public BookingController(BookingService bookingService , EmailService emailService , PdfService pdfService) {
        this.bookingService = bookingService;
        this.emailService=emailService;
        this.pdfService=pdfService;
    }

    //http://localhost:8080/api/bookings?busId=abc&ticketId=xyz
    //busId & ticketId will come from URL
    @PostMapping
    public ResponseEntity<BookingDetailsDto> bookingBus(@RequestParam("busId") String  busId,
                                                        @RequestParam("ticketId") String ticketId ,
                                                        @RequestBody PassengerDetails passengerDetails
    ){

        BookingDetailsDto booking = bookingService.createBooking(busId, ticketId, passengerDetails);


        //to send email without Attachment
        if(booking!=null) {
            emailService.sendEmail(passengerDetails.getEmail(),
                    "Booking confirmed with Booking ID :" +booking.getBookingId(),
                    "Your Booking is confirmed \n Name : " +passengerDetails.getFirstName() +passengerDetails.getLastName() +"\n Email :" +passengerDetails.getEmail() +" \n Mobile : "+ passengerDetails.getMobile());
        }

        // with Attachment8
        if(booking!=null) {

            //to generate PDF
            byte[] pdfBytes = pdfService.generatePdf(booking);

            //send confirmation email with PDF attachment
            sendBookingConfirmationEmailWithAttachment(passengerDetails ,booking , pdfBytes);

        }



        return  new ResponseEntity<>(booking , HttpStatus.CREATED);
    }

    private void sendBookingConfirmationEmailWithAttachment(PassengerDetails passengerDetails, BookingDetailsDto booking, byte[] pdfBytes) {

        String emailSubject = "Booking Confirmed. Booking Id  : "+booking.getBookingId();

        String emailBody = String.format("Your Booking is confirmed /n Name : %s %s" , passengerDetails.getFirstName() , passengerDetails.getLastName());


        //Attach PDF to the email
        emailService.sendEmailWithAttachment(passengerDetails.getEmail(), emailSubject , emailBody , pdfBytes ,"BookingConfirmation.pdf" );
    }
}
