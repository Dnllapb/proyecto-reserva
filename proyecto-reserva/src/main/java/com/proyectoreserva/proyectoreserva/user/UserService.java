package com.proyectoreserva.proyectoreserva.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    //Este es un constructor de tipo lista
    public List<Users> getUser()
    {
        return this.userRepository.findAll();
    }
    public ResponseEntity<Object> newUser(Users user) {
      Optional <Users>  result =  userRepository.findUserByName(user.getName());
        if (result.isPresent())
        {
            return new ResponseEntity<>
                    (
                            HttpStatus.CONFLICT
                    );
            //throw new IllegalStateException("El usuario ya existe ");
        }
        userRepository.save(user);
        return new ResponseEntity<>(
                HttpStatus.CREATED
        );
    }
}

