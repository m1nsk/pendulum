package com.minsk.pendulum.converter;

import com.minsk.pendulum.DTO.DtoUtils;
import com.minsk.pendulum.DTO.user.UserCreateDto;
import com.minsk.pendulum.DTO.user.UserDto;
import com.minsk.pendulum.DTO.user.UserFullDto;
import com.minsk.pendulum.model.User;
import com.minsk.pendulum.service.UserService;
import com.minsk.pendulum.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

import static com.minsk.pendulum.util.ValidationUtil.checkNotFound;
import static com.minsk.pendulum.util.ValidationUtil.checkNotFoundWithId;

@Service
@Transactional
public class UserConverterImpl {

    private UserService service;

    private DtoUtils dtoUtils;

    @Autowired
    public UserConverterImpl(UserService service, DtoUtils dtoUtils) {
        this.service = service;
        this.dtoUtils = dtoUtils;
    }
    
    public UserDto create(UserCreateDto userCreateDto) {
        User user = service.create(dtoUtils.convertToEntity(userCreateDto));
        return dtoUtils.convertToDto(user);
    }

    public void delete(int id) {
        service.delete(id);
    }

    public UserDto get(int id) {
        return dtoUtils.convertToDto(service.get(id));
    }

    public UserDto getByEmail(String email) {
        return dtoUtils.convertToDto(service.getByEmail(email));
    }

    public List<UserDto> getAll() {
        return service.getAll().stream().map(item -> dtoUtils.convertToDto(item)).collect(Collectors.toList());
    }

    public void update(UserDto userDto) {
        service.create(dtoUtils.convertToEntity(userDto));
    }

    public UserFullDto getFull(int id) {
        return dtoUtils.convertToFullDto(service.get(id));
    }

    public List<UserFullDto> getAllFull() {
        return service.getAll().stream().map(item -> dtoUtils.convertToFullDto(item)).collect(Collectors.toList());
    }
}