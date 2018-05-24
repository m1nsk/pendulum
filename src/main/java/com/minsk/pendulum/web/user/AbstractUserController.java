package com.minsk.pendulum.web.user;

import com.minsk.pendulum.DTO.DtoUtils;
import com.minsk.pendulum.DTO.user.UserCreateDto;
import com.minsk.pendulum.DTO.user.UserDto;
import com.minsk.pendulum.DTO.user.UserFullDto;
import com.minsk.pendulum.converter.UserConverterImpl;
import com.minsk.pendulum.model.Role;
import com.minsk.pendulum.web.AbstractSecurityController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static com.minsk.pendulum.util.ValidationUtil.assureIdConsistent;

public abstract class AbstractUserController extends AbstractSecurityController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserConverterImpl converter;

    @Autowired
    private DtoUtils dtoUtils;

    public List<UserDto> getAll() {
        return converter.getAll();
    }

    public List<UserFullDto> getAllFull() {
        return converter.getAllFull();
    }

    public UserDto get(int id) {
        return converter.get(id);
    }

    public UserFullDto getFull(int id) {
        return converter.getFull(id);
    }

    public UserDto create(UserCreateDto userCreateDto) {
        userCreateDto.setRoles(new HashSet<>(Arrays.asList(Role.ROLE_USER)));
        return converter.create(userCreateDto);
    }

    public void delete(int id) {
        converter.delete(id);
    }

    public void update(UserDto userDto, int id) {
        assureIdConsistent(userDto, id);
        converter.update(userDto);
    }

    public UserDto getByMail(String email) {
        return converter.getByEmail(email);
    }
    
}