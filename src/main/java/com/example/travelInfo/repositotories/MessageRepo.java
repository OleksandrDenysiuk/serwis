package com.example.travelInfo.repositotories;

import com.example.travelInfo.domain.Message;
import com.example.travelInfo.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message,Long> {
    List<Message> findAllByAuthor (User user);

    List<Message> findAllByUserFor(User user);
}
