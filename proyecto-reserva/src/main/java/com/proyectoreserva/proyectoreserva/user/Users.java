package com.proyectoreserva.proyectoreserva.user;

import com.proyectoreserva.proyectoreserva.lasting.ERole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(schema= "\"users\"" , uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Users implements UserDetails {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   private Integer id_number;
    @Column(unique = true)
    private String name;
    private String email_user;

    private String password;
    private Boolean enable;


    @Enumerated(EnumType.ORDINAL)
    private ERole role;

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


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email_user;
    }
    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enable;
    }
}
