package com.minsk.pendulum.DTO.channel;

import com.minsk.pendulum.DTO.message.MessageDto;
import com.minsk.pendulum.DTO.user.UserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChannelDto {

    private Integer id;

    private String name;

    private MessageDto message;

    private UserDto user;

}
