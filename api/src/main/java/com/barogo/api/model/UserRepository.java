package com.barogo.api.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDTO, Long> {

    // public UserDTO findById(String id);

    Optional<UserDTO> findById(String id);

}
