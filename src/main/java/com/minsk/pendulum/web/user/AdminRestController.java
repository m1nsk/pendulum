package com.minsk.pendulum.web.user;

import com.minsk.pendulum.DTO.user.UserCreateDto;
import com.minsk.pendulum.DTO.user.UserDto;
import com.minsk.pendulum.DTO.user.UserFullDto;
import com.minsk.pendulum.model.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(AdminRestController.REST_URL)
public class AdminRestController extends AbstractUserController {
    static final String REST_URL = "/rest/admin/users";

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserFullDto> getAllFull() {
        return super.getAllFull();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserFullDto getFull(@PathVariable("id") int id) {
        return super.getFull(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto createWithLocation(@RequestBody UserCreateDto userCreateDto) {
        userCreateDto.setRoles(new HashSet<>(Arrays.asList(Role.ROLE_ADMIN)));
        UserDto created = super.create(userCreateDto);
        return created;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody UserDto userDto, @PathVariable("id") int id) {
        super.update(userDto, id);
    }

    @Override
    @GetMapping(value = "/by", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto getByMail(@RequestParam("email") String email) {
        return super.getByMail(email);
    }
}