package com.minsk.pendulum.web.user;

import com.minsk.pendulum.DTO.user.UserCreateDto;
import com.minsk.pendulum.DTO.user.UserDto;
import com.minsk.pendulum.DTO.user.UserFullDto;
import com.minsk.pendulum.model.Role;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(AdminRestController.REST_URL)
public class AdminRestController extends AbstractUserController {
    static final String REST_URL = "/rest/admin/users";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserFullDto> getAllFullInfo() {
        return super.getAllFull();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserFullDto getFullInfo(@PathVariable("id") int id) {
        return super.getFull(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto createWithLocation(@RequestBody UserCreateDto userCreateDto) {
        userCreateDto.setRoles(new HashSet<>(Arrays.asList(Role.ROLE_ADMIN)));
        UserDto created = super.create(userCreateDto);
        return created;
    }
}