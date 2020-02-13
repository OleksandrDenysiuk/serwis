package com.example.travelInfo.repositotories;


import com.example.travelInfo.domain.Role;
import com.example.travelInfo.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    User findByRoles(Role role);
}