package com.redbus.User.controller;

import com.redbus.User.payload.BusListDto;
import com.redbus.User.service.SearchBusesService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/user")
public class FindBusesController {

    private SearchBusesService searchBusesService;
    public FindBusesController(SearchBusesService searchBusesService) {
        this.searchBusesService = searchBusesService;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // convert it to Dto **
    //we can give by using JSON Obj --> create another dto to capture the date

    //http://localhost:8080/api/user/searchBuses?departureCity=CityA&arrivalCity=CityB&departureDate=12/12/2023
    @GetMapping("/searchBuses")
    public List<BusListDto> searchBuses(@RequestParam  String departureCity ,
                                        @RequestParam String arrivalCity ,
                                        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")  Date departureDate){

                                  // @DateTimeFormat(pattern = "yyyy-MM-dd")  -->  it will convert String(from URL -->
                                  // (departureDate=2023-12-12)) to Date type(java.util.Date) & search the data in Database ***********

        List<BusListDto> busListDtos = searchBusesService.searchBusBy(departureCity, arrivalCity, departureDate);

        return busListDtos;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
