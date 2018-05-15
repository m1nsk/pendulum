package com.minsk.pendulum.web.user;

import com.minsk.pendulum.DTO.DtoUtils;
import com.minsk.pendulum.DTO.user.UserCreateDto;
import com.minsk.pendulum.DTO.user.UserDto;
import com.minsk.pendulum.DTO.user.UserFullDto;
import com.minsk.pendulum.model.User;
import com.minsk.pendulum.service.UserService;
import com.minsk.pendulum.web.AbstractSecurityController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static com.minsk.pendulum.util.ValidationUtil.assureIdConsistent;
import static com.minsk.pendulum.util.ValidationUtil.checkNew;

public abstract class AbstractUserController extends AbstractSecurityController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    @Autowired
    private DtoUtils dtoUtils;

    public List<UserDto> getAll() {
        List<User> users = service.getAll();
        return users.stream().map(user -> dtoUtils.convertToDto(user)).collect(Collectors.toList());
    }

    public List<UserFullDto> getAllFull() {
        List<User> users = service.getAll();
        return users.stream().map(user -> dtoUtils.convertToFullDto(user)).collect(Collectors.toList());
    }

    public UserDto get(int id) {
        UserDto userDto = dtoUtils.convertToDto(service.get(id));
        return userDto;
    }

    public UserFullDto getFull(int id) {
        UserFullDto userDto = dtoUtils.convertToFullDto(service.get(id));
        return userDto;
    }

    public UserDto create(UserCreateDto userCreateDto) {
        checkNew(userCreateDto);
        User user = dtoUtils.convertToEntity(userCreateDto);
        user = service.create(user);
        return dtoUtils.convertToDto(user);
    }

    public void delete(int id) {
        service.delete(id);
    }

    public void update(UserDto userDto, int id) {
        assureIdConsistent(userDto, id);
        User user = dtoUtils.convertToEntity(userDto);
        service.update(user);
    }

    public UserDto getByMail(String email) {
        User user = service.getByEmail(email);
        return dtoUtils.convertToDto(user);
    }
    
}