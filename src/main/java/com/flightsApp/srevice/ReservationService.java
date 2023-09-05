package com.flightsApp.srevice;

import com.flightsApp.dto.ReservationRequest;
import com.flightsApp.entity.Reservation;

public interface ReservationService {

	Reservation bookFlight(ReservationRequest request);

}
