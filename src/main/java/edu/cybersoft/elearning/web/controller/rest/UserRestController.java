package edu.cybersoft.elearning.web.controller.rest;

import edu.cybersoft.elearning.dto.model.LoginDto;
import edu.cybersoft.elearning.dto.model.UserDto;
import edu.cybersoft.elearning.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = { "api/auth" })
public class UserRestController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Autowired
    public UserRestController(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping(path = { "/sign-up" })
    public Object addUser(@RequestBody UserDto userDto) {
        try {
            this.userService.add(userDto);
            return new ResponseEntity<String>("Successfully added new user.", HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured adding a new user. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = { "/sign-in" })
    public Object login(@RequestBody LoginDto loginDto) {
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
            authentication = authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            Date now = new Date();
            String JsonWebToken = Jwts.builder()
                    .setSubject(loginDto.getEmail())
                    .setIssuedAt(now)
                    .setExpiration(new Date(now.getTime() + 864000000L))
                    .signWith(SignatureAlgorithm.HS512, "Hieu")
                    .compact();
            return new ResponseEntity<String>(JsonWebToken, HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = { "" })
    public Object getUsers() {
        try {
            List<UserDto> userDtos = this.userService.findAll();
            return new ResponseEntity<Object>(userDtos, HttpStatus.FOUND);
        } catch (Exception exception) {
            return new ResponseEntity<String>("No users exist.", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(path = { "/{id}" })
    public Object getUser(@PathVariable("id") Long id) {
        try {
            UserDto userDto = this.userService.findById(id);
            return new ResponseEntity<Object>(userDto, HttpStatus.FOUND);
        } catch (Exception exception) {
            return new ResponseEntity<String>("User not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = { "" })
    public Object updateUser(@RequestBody UserDto userDto) {
        try {
            this.userService.update(userDto);
            return new ResponseEntity<String>("Successfully updated user.", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured updating user. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = { "/{id}" })
    public Object removeUser(@PathVariable("id") Long id) {
        try {
            this.userService.delete(id);
            return new ResponseEntity<String>("User removed.", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<String>(String.format("Unknown error(s) occured removing user. \nError details: %s", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}