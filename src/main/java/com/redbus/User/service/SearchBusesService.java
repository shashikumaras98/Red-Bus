package com.redbus.User.service;

import com.redbus.User.payload.BusListDto;
import com.redbus.operator.entity.BusOperator;
import com.redbus.operator.repository.BusOperatorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchBusesService {

    private BusOperatorRepository busOperatorRepo;

    public SearchBusesService(BusOperatorRepository busOperatorRepo) {
        this.busOperatorRepo = busOperatorRepo;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //convert to Dto
    public List<BusListDto> searchBusBy(String departureCity , String arrivalCity , Date departureDate){

        List<BusOperator> busesAvailable = busOperatorRepo.findByDepartureCityAndArrivalCityAndDepartureDate(departureCity, arrivalCity, departureDate);

        List<BusListDto> dtos = busesAvailable.stream().map(bus -> mapToDto(bus)).collect(Collectors.toList());

        return dtos; // convert it to Dto(dto --> will return only the required details to th USer)
                               // (when we return entity will give all the details present in the DB)
    }

    private BusListDto mapToDto(BusOperator busAvailable) {
        
        BusListDto dto =new BusListDto();
        
        dto.setBusId(busAvailable.getBusId());
        dto.setBusNumber(busAvailable.getBusNumber());
        dto.setBusOperatorCompanyName(busAvailable.getBusOperatorCompanyName());
        dto.setNumberOfSeats(busAvailable.getNumberOfSeats());
        dto.setDepartureCity(busAvailable.getDepartureCity());
        dto.setArrivalCity(busAvailable.getArrivalCity());
        dto.setDepartureTime(busAvailable.getDepartureTime());
        dto.setArrivalTime(busAvailable.getArrivalTime());
        dto.setDepartureDate(busAvailable.getDepartureDate());
        dto.setArrivalDate(busAvailable.getArrivalDate());
        dto.setTotalTravelTime(busAvailable.getTotalTravelTime());
        dto.setBusType(busAvailable.getBusType());
        dto.setAmenities(busAvailable.getAmenities());
        
        return dto;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
