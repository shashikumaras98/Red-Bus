package com.redbus.operator.repository;

import com.redbus.operator.entity.BusOperator;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;

import javax.persistence.TemporalType;
import java.util.Date; //****
import java.util.List;
public interface BusOperatorRepository extends JpaRepository<BusOperator, String> {

    List<BusOperator> findByDepartureCityAndArrivalCityAndDepartureDate(String departureCity,
                                                                        String arrivalCity,
                                                                        Date departureDate);
}

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                                           //or
     // we can use Query annotation --> just like SQL on the Top of the required Custom method
    //bo alternative name given to --> BusOperator

    //
//    @Query("SELECT bo FROM BusOperator bo  WHERE bo.departureCity = :departureCity  AND  " +
//                                                "bo.arrivalCity = :arrivalCity AND  " +
//                                                "bo. departureDate = :Date")  // this will perform HQL query
//    //& it will search date from DB and give
//    List<BusOperator> searchByCitiesAndDate(@Param("departureCity") String departureCity,
//                                            @Param("arrivalCity") String arrivalCity,
//                                            @Param("Date") Date departureDate);

    // to search by email
    //@Query("select bo from BusOperator bo where bo.email= :email")
    //Optional<BusOperator> searchByEmail(@Param("email") String email);
//}
