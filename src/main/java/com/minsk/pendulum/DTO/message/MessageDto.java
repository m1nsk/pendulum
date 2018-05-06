package com.minsk.pendulum.DTO.message;

import com.minsk.pendulum.DTO.user.UserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDto {

    private Integer id;

    private String message;

    private UserDto user;

    private int channelId;

}
