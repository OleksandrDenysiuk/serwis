package com.example.travelInfo.repositotories;

import com.example.travelInfo.domain.Comment;
import com.example.travelInfo.domain.Place;
import com.example.travelInfo.domain.Trip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends CrudRepository<Comment, Long> {

    List<Comment> findAllByPlace(Place place);

    List<Comment> findAllByTrip(Trip trip);
}
