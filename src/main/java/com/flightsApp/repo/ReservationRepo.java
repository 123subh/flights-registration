package com.flightsApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightsApp.entity.Reservation;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {

}
