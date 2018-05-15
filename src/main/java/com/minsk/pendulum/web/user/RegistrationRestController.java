package com.minsk.pendulum.web.user;

import com.minsk.pendulum.DTO.user.UserCreateDto;
import com.minsk.pendulum.DTO.user.UserDto;
import com.minsk.pendulum.model.Role;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(RegistrationRestController.REST_URL)
public class RegistrationRestController extends AbstractUserController {
    static final String REST_URL = "/registration";

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto createWithLocation(@RequestBody UserCreateDto userCreateDto) {
        userCreateDto.setRoles(new HashSet<>(Arrays.asList(Role.ROLE_USER)));
        UserDto created = super.create(userCreateDto);
        return created;
    }
}