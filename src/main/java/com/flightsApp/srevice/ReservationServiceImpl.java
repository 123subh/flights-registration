package com.flightsApp.srevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightsApp.dto.ReservationRequest;
import com.flightsApp.entity.Flight;
import com.flightsApp.entity.Passenger;
import com.flightsApp.entity.Reservation;
import com.flightsApp.repo.FlightRepo;
import com.flightsApp.repo.PassengerRepo;
import com.flightsApp.repo.ReservationRepo;

@Service
public class ReservationServiceImpl implements ReservationService
{
	@Autowired
	private FlightRepo repo;
	@Autowired
	private PassengerRepo passengerRepo;
	@Autowired
	private ReservationRepo reservationRepo;

	@Override
	public Reservation bookFlight(ReservationRequest request) {
		Flight flight = repo.findById(request.getId()).get();
		
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getFirstName());
		passenger.setLastName(request.getLastName());
		passenger.setMiddleName(request.getMiddleName());
		passenger.setEmail(request.getEmail());
		passenger.setPhone(request.getPhone());
		
		passengerRepo.save(passenger);
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(passenger);
		reservation.setCheckedIn(false);
		reservation.setNumberOfBags(0);
		
		reservationRepo.save(reservation);
		
		return reservation;
	}

	
}
