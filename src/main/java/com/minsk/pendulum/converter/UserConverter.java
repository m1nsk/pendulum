package com.minsk.pendulum.converter;


import com.minsk.pendulum.DTO.user.UserCreateDto;
import com.minsk.pendulum.DTO.user.UserDto;
import com.minsk.pendulum.DTO.user.UserFullDto;
import com.minsk.pendulum.util.exception.NotFoundException;

import java.util.List;

public interface UserConverter {

    UserDto create(UserCreateDto userCreateDto);

    void delete(int id) throws NotFoundException;

    UserDto get(int id) throws NotFoundException;

    UserFullDto getFull(int id) throws NotFoundException;

    UserDto getByEmail(String email) throws NotFoundException;

    void update(UserDto UserDto);

    List<UserDto> getAll();

    List<UserFullDto> getAllFull();
}