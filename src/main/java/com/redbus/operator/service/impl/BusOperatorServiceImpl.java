package com.redbus.operator.service.impl;

import com.redbus.operator.entity.BusOperator;
import com.redbus.operator.entity.TicketCost;
import com.redbus.operator.payload.BusOperatorDto;
import com.redbus.operator.repository.BusOperatorRepository;
import com.redbus.operator.repository.TicketCostRepository;
import com.redbus.operator.service.BusOperatorService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BusOperatorServiceImpl implements BusOperatorService {

    private BusOperatorRepository busOperatorRepo;
    private ModelMapper modelMapper;
    private TicketCostRepository ticketCostRepository;

    public BusOperatorServiceImpl(BusOperatorRepository busOperatorRepo  ,  ModelMapper modelMapper ,TicketCostRepository ticketCostRepository) {
        this.busOperatorRepo = busOperatorRepo;
        this.modelMapper = modelMapper;
        this.ticketCostRepository=ticketCostRepository;

    }

    @Override
    public BusOperatorDto scheduleBus(BusOperatorDto busOperatorDto

                                     ) {

        BusOperator busOperator = maptoEntity(busOperatorDto);//dto to entity

        String busId = UUID.randomUUID().toString();  // this will help me to generate Unique Bus Id
        busOperator.setBusId(busId); //& then i'am going to set that with busId
        //even though we supply busId from Postman --> it desn't matter --> it will save dynamically generated busId to the DB

        //////// to save Ticket details with Bus details
        TicketCost ticketCost = new TicketCost();

        ticketCost.setBusId(busId); //set busId --> which will appear in ticket_cost table  // -->otherwise we get null --> in both Postman response & in cost table

        ticketCost.setTicketId(busOperatorDto.getTicketCost().getTicketId());
        ticketCost.setCost(busOperatorDto.getTicketCost().getCost());
        ticketCost.setCode(busOperatorDto.getTicketCost().getCode());
        ticketCost.setDiscountAmount(busOperatorDto.getTicketCost().getDiscountAmount());

        //set the TicketCost entity ans set its properties
        busOperator.setTicketCost(ticketCost);
        //////

        BusOperator saveBusSchedule = busOperatorRepo.save(busOperator);// save it to DB

        BusOperatorDto dto = maptoDto(saveBusSchedule);//entity to dto

        return dto;

    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private BusOperator maptoEntity(BusOperatorDto busOperatorDto) {

        BusOperator entity = modelMapper.map(busOperatorDto , BusOperator.class);

        entity.setDepartureDate(busOperatorDto.getDepartureDate());//manual

        return entity;
    }

    private BusOperatorDto maptoDto(BusOperator busOperator) {

        BusOperatorDto dto = modelMapper.map(busOperator , BusOperatorDto.class);

        dto.setDepartureDate(busOperator.getDepartureDate());//manual

        return dto;
    }


}
