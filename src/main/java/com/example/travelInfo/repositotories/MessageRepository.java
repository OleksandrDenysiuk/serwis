package com.example.travelInfo.repositotories;


import com.example.travelInfo.domain.Place;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Place, Integer> {
}
