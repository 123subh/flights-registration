package com.flightsApp.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flightsApp.entity.Flight;
import com.flightsApp.repo.FlightRepo;
import com.flightsApp.srevice.ReservationService;

@Controller
public class FlightController {
	@Autowired
	private FlightRepo flightRepo;


	@RequestMapping(path="/findFlights")
	public String findFlights(
			@RequestParam("from")String from,@RequestParam("to")String to,
			@RequestParam("departureDate")@DateTimeFormat(pattern="MM-dd-yyy")Date departureDate,
			ModelMap model
			) {
		List<Flight> findFlights = flightRepo.findFlights(from,to,departureDate);
		model.addAttribute("flights",findFlights);
		return "displayFlights";
	}

	
		
	
}
