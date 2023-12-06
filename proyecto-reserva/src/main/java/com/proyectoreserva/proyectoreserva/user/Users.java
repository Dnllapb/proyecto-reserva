package com.proyectoreserva.proyectoreserva.user;

import jakarta.persistence.*;

@Entity
@Table(schema= "\"users\"" )
public class Users {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   private Integer id_number;
    @Column(unique = true)
    private String name;
    private String email_user;

    private String password;

    public Users() {
    }

    public Users(Integer id, Integer id_number, String name, String email_user, String password) {
        this.id = id;
        this.id_number = id_number;
        this.name = name;
        this.email_user = email_user;
        this.password = password;
    }

    public Users(Integer id_number, String name, String email_user, String password) {
        this.id_number = id_number;
        this.name = name;
        this.email_user = email_user;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_number() {
        return id_number;
    }

    public void setId_number(Integer id_number) {
        this.id_number = id_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
