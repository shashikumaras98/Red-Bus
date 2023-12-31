package com.redbus.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.redbus.User.payload.BookingDetailsDto;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    public byte[] generatePdf(BookingDetailsDto bookingDetails) {  // it is taking Booking details  & then after it is creating one Pdf(document)
        Document document = new Document();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();

            // Add content to the PDF
            document.add(new Paragraph("Booking ID: " + bookingDetails.getBookingId()));
            document.add(new Paragraph("Bus Company: " + bookingDetails.getBusCompany()));
            document.add(new Paragraph("From: " + bookingDetails.getFrom()));
            document.add(new Paragraph("To: " + bookingDetails.getTo()));
            document.add(new Paragraph("First Name: " + bookingDetails.getFirstName()));
            document.add(new Paragraph("Last Name: " + bookingDetails.getLastName()));
            document.add(new Paragraph("Email: " + bookingDetails.getEmail()));
            document.add(new Paragraph("Mobile: " + bookingDetails.getMobile()));
            document.add(new Paragraph("Price: " + bookingDetails.getPrice()));

        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }

        return byteArrayOutputStream.toByteArray();
    }
}
