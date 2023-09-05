package com.flightsApp.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flightsApp.dto.ReservationRequest;
import com.flightsApp.entity.Flight;
import com.flightsApp.entity.Reservation;
import com.flightsApp.repo.FlightRepo;
import com.flightsApp.srevice.ReservationService;
import com.flightsApp.utils.EmailUtil;
import com.flightsApp.utils.PdfGenerator;

@Controller
public class ReservationController {

	private static String filePath = "C:\\Users\\User\\Documents\\workspace-spring-tool-suite-4-4.14.1.RELEASE\\FlightsApp\\tickets\\bookings";
	@Autowired
	private FlightRepo flightRepo;
	@Autowired
	private ReservationService reservationService;

	@RequestMapping("/showCompleteReservation")
	public String completeReservation(@RequestParam("fightId") Long flightId, ModelMap modelMap) {
		System.out.println(flightId);
		Optional<Flight> findById = flightRepo.findById(flightId);
		Flight flight = findById.get();
		modelMap.addAttribute("flight", flight);
		return "showCompleteReservation";
	}

	@RequestMapping(path = "/confirmRegistration")
	public String confirmRegistration(ReservationRequest request, ModelMap modal) {
		Reservation bookFlight = reservationService.bookFlight(request);

		PdfGenerator pdf = new PdfGenerator();
		pdf.generatePDF(filePath + bookFlight.getId() + ".pdf", bookFlight.getPassenger().getFirstName(),
				bookFlight.getPassenger().getEmail(), bookFlight.getPassenger().getPhone(),
				bookFlight.getFlight().getOperatingAirlines(), bookFlight.getFlight().getDateOfDeparture(),
				bookFlight.getFlight().getDepartureCity(), bookFlight.getFlight().getArrivalCity());
		modal.addAttribute("bookFlight", bookFlight.getId());
		EmailUtil util = new EmailUtil();
		String attachment=filePath + bookFlight.getId() + ".pdf";
		util.sendItinerary(request.getEmail(), attachment);
		return "finalConfermation";
	}
}
