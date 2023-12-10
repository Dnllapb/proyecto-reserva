package com.proyectoreserva.proyectoreserva.repository;

import com.proyectoreserva.proyectoreserva.user.Users;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Integer> {
   Optional<Users> findUserByName(String name_user);

   Optional <Users> findUserByEmail(String email_user);
}
