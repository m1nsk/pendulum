package com.minsk.pendulum.web.user;

import com.minsk.pendulum.DTO.user.UserCreateDto;
import com.minsk.pendulum.DTO.user.UserDto;
import com.minsk.pendulum.model.Role;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(RegistrationRestController.REST_URL)
public class RegistrationRestController extends AbstractUserController {
    static final String REST_URL = "/registration";

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto create(@Valid @RequestBody UserCreateDto userCreateDto) {
        userCreateDto.setRoles(new HashSet<>(Arrays.asList(Role.ROLE_USER)));
        UserDto created = super.create(userCreateDto);
        return created;
    }
}