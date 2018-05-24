package com.minsk.pendulum.DTO.channel;

import com.minsk.pendulum.DTO.AbstractBaseDto;
import com.minsk.pendulum.DTO.message.MessageDto;
import com.minsk.pendulum.DTO.user.UserDto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ChannelDto extends AbstractBaseDto {

    @NotBlank
    @Length(min = 3, max = 30)
    private String name;

    @NotNull
    private MessageDto message;

    private UserDto user;

}
