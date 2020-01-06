package com.example.travelInfo.repositotories;

import com.example.travelInfo.domain.Comment;
import com.example.travelInfo.domain.Place;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends CrudRepository<Comment, Long> {

    List<Comment> findAllByPlaceOrderByIdDesc(Place place);

    List<Comment> findAllByPlace(Place place);
}
