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
public class UserConverterImpl implements UserConverter {

    private UserService service;

    private DtoUtils dtoUtils;

    @Autowired
    public UserConverterImpl(UserService service, DtoUtils dtoUtils) {
        this.service = service;
        this.dtoUtils = dtoUtils;
    }

    @Override
    public UserDto create(UserCreateDto userCreateDto) {
        User user = service.create(dtoUtils.convertToEntity(userCreateDto));
        return dtoUtils.convertToDto(user);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        service.delete(id);
    }

    @Override
    public UserDto get(int id) throws NotFoundException {
        return dtoUtils.convertToDto(service.get(id));
    }

    @Override
    public UserDto getByEmail(String email) throws NotFoundException {
        return dtoUtils.convertToDto(service.getByEmail(email));
    }

    @Override
    public List<UserDto> getAll() {
        return service.getAll().stream().map(item -> dtoUtils.convertToDto(item)).collect(Collectors.toList());
    }

    @Override
    public void update(UserDto userDto) {
        service.create(dtoUtils.convertToEntity(userDto));
    }

    @Override
    public UserFullDto getFull(int id) throws NotFoundException {
        return dtoUtils.convertToFullDto(service.get(id));
    }

    @Override
    public List<UserFullDto> getAllFull() {
        return service.getAll().stream().map(item -> dtoUtils.convertToFullDto(item)).collect(Collectors.toList());
    }
}