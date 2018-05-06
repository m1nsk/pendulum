package com.minsk.pendulum.web.user;

import com.minsk.pendulum.DTO.DtoUtils;
import com.minsk.pendulum.DTO.user.UserCreateDto;
import com.minsk.pendulum.DTO.user.UserDto;
import com.minsk.pendulum.model.User;
import com.minsk.pendulum.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static com.minsk.pendulum.util.ValidationUtil.assureIdConsistent;
import static com.minsk.pendulum.util.ValidationUtil.checkNew;

public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    @Autowired
    private DtoUtils dtoUtils;

    public List<UserDto> getAll() {
        log.info("getAll");
        List<User> users = service.getAll();
        return users.stream().map(user -> dtoUtils.convertToDto(user)).collect(Collectors.toList());
    }

    public UserDto get(int id) {
        log.info("get {}", id);
        UserDto userDto = dtoUtils.convertToDto(service.get(id));
        return userDto;
    }

    public UserDto create(UserCreateDto userCreateDto) {
        log.info("create {}", userCreateDto);
        User user = dtoUtils.convertToEntity(userCreateDto);
        checkNew(user);
        user = service.create(user);
        return dtoUtils.convertToDto(user);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(UserDto userDto, int id) {
        log.info("update {} with id={}", userDto, id);
        User user = dtoUtils.convertToEntity(userDto);
        assureIdConsistent(user, id);
        service.update(user);
    }

    public UserDto getByMail(String email) {
        log.info("getByEmail {}", email);
        User user = service.getByEmail(email);
        return dtoUtils.convertToDto(user);
    }
    
}