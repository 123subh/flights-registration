package com.flightsApp.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flightsApp.entity.Flight;
import com.flightsApp.entity.Reservation;

public interface FlightRepo extends JpaRepository<Flight, Long> {

	@Query("from Flight where departureCity=:departureCity and arrivalCity=:arrivalCity and dateOfDeparture=:dateOfDeparture")
	List<Flight> findFlights(@Param("departureCity") String from,@Param("arrivalCity")String to,@Param("dateOfDeparture")Date departureDate);
}
