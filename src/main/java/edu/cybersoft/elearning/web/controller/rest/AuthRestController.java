package edu.cybersoft.elearning.web.controller.rest;

import edu.cybersoft.elearning.dto.model.LoginDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(path = { "/api/auth" })
public class AuthRestController {
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthRestController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(path = { "/login" })
    public Object login(@RequestBody LoginDto loginDto) {
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
            authentication = authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            Date now = new Date();
            String JWT = Jwts.builder()
                    .setSubject(loginDto.getEmail())
                    .setIssuedAt(now)
                    .setExpiration(new Date(now.getTime() + 864000000L))
                    .signWith(SignatureAlgorithm.HS512, "Cybersoft")
                    .compact();
            return new ResponseEntity<String>(JWT, HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }
}