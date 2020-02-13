package com.example.travelInfo.repositotories;


import com.example.travelInfo.domain.Place;
import com.example.travelInfo.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends CrudRepository<Place, Long>{

    List<Place> findAll();

    List<Place> findAllByOrderByDateDesc();

    List<Place> findAllByOrderByDateAsc();

    List<Place> findAllByOrderByRatingAsc();

    List<Place> findAllByOrderByRatingDesc();

    List<Place> findAllByAddress(String address);

    List<Place> findAllByType(String type);

    List<Place> findAllByAuthor(User user);

    List<Place> findAllByTags(String tag);

}
