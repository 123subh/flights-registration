package com.flightsApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightsApp.entity.Passenger;

public interface PassengerRepo extends JpaRepository<Passenger, Long> {

}
