package com.minsk.pendulum.DTO.message;

import com.minsk.pendulum.DTO.AbstractBaseDto;
import com.minsk.pendulum.DTO.channel.ChannelDto;
import com.minsk.pendulum.DTO.user.UserDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageDto extends AbstractBaseDto {

    private String message;

    private UserDto user;

    private LocalDateTime date;
}
