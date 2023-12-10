package com.proyectoreserva.proyectoreserva.config;

import com.proyectoreserva.proyectoreserva.service.JwtServices;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor// constructor con atributos fiaales
public class JwtFilter  extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;//componente de negocio
    private final JwtServices jwtService; // componente de negocio  objeto para usar


    //transformar las declaraciones de usuario en un json web filtra una x una
    @Override//hacer filtro interno
    protected void doFilterInternal(HttpServletRequest request,// peticion
                                    HttpServletResponse response,//respuesta a la peticion
                                    FilterChain filterChain)//cadena de filtrado
            throws ServletException, IOException {


        final String authHeader = request.getHeader("Authorization");//del encabezado obtenga un autributo autorization y se guarda en una variable
        final String jwt;
        final String userEmail;


        if (authHeader == null || authHeader.startsWith("Bearer "))//valindao si no empieza con el prefijo Bearer si tiene valor  que no sea un jwt
        {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);//pasar la posicion virtual de 0 a 7
        userEmail = jwtService.extractUsername(jwt);// recibiendo el jwt como una cadena de caracteres
        if (userEmail != null && SecurityContextHolder.getContext() == null)// si tenemos un correo pero  no esta logueado ya
        {
            //Userdetailservices en una clase que hace parte del contexto de
            // SpringSecurity porque ella tienen todo por dentro y obtener una respuesta le delegamos todo el manejo de la autenticacion
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);// nos va a traer lo que este en bases de datos
            setAuthenticationToContext(request,jwt,userDetails);
        }


    }

    private void setAuthenticationToContext(HttpServletRequest request, String jwt, UserDetails userDetails) {// de uso interno
        //validar si el token es valido

        if (jwtService.isTokenValid(jwt, userDetails))
        {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                 userDetails,
                         null,
                         userDetails.getAuthorities()


            );
            //Asignar la info que viene del jtw al security context holder
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }


}
