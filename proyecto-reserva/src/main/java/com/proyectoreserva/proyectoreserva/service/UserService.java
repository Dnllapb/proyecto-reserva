package com.proyectoreserva.proyectoreserva.service;

import com.proyectoreserva.proyectoreserva.repository.UserRepository;
import com.proyectoreserva.proyectoreserva.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    HashMap <String,Object> hashMap = new HashMap<>();
    @Autowired
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    //Este es un constructor de tipo lista
    public List<Users> getUser() {
        return this.userRepository.findAll();
    }

    public ResponseEntity<Object> newUser(Users user) {
        Optional<Users> result = userRepository.findUserByName(user.getName());


        if (result.isPresent()) {
            hashMap.put("Error", true);
            hashMap.put("Message", "User already exists");
            return new ResponseEntity<>
                    (       hashMap,
                            HttpStatus.CONFLICT
                    );
            //throw new IllegalStateException("El usuario ya existe ");
        }
        userRepository.save(user);
        hashMap.put("Info", user);
        hashMap.put("Message", "User save");

        return new ResponseEntity<>(
                hashMap,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> updateUser(Users user) {
        Optional<Users> result = userRepository.findUserByName(user.getName());


        if (result.isPresent() && user  .getId() == null) {
            hashMap.put("Error", true);
            hashMap.put("Message", "User already exists");
            return new ResponseEntity<>
                    (       hashMap,
                            HttpStatus.CONFLICT
                    );
            //throw new IllegalStateException("El usuario ya existe ");
        }
        hashMap.put("Message", "User save");
        if (user.getId() != null)
        {
            hashMap.put("message","user update");
        }
        userRepository.save(user);
        hashMap.put("Info", user);

        return new ResponseEntity<>(
                hashMap,
                HttpStatus.CREATED
        );
    }

    public  ResponseEntity<Object> deleteUser(Integer id)
    {
      boolean existsId = this.userRepository.existsById(id);
      if(!existsId)
      {
          hashMap.put("Error", true);
          hashMap.put("Message", "user does not exist with that id");
          return new ResponseEntity<>
                  (       hashMap,
                          HttpStatus.CONFLICT
                  );
      }
        userRepository.deleteById(id);
        hashMap.put("Message", "user delete");
        return new ResponseEntity<>
                (       hashMap,
                        HttpStatus.ACCEPTED
                );
    }
    }



