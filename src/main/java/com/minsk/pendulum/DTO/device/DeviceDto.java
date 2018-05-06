package com.minsk.pendulum.DTO.device;

import com.minsk.pendulum.DTO.user.UserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceDto {

    private Integer id;

    private String name;

    private int serial;

    private UserDto user;
}

