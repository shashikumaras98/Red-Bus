package com.redbus.User.repository;

import com.redbus.User.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking , String> {

}
