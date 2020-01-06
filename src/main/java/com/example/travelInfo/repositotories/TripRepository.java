package com.example.travelInfo.repositotories;

import com.example.travelInfo.domain.Trip;
import com.example.travelInfo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findAllByAuthor(User user);

}
