package com.minsk.pendulum.DTO.message;

import com.minsk.pendulum.DTO.AbstractBaseDto;
import com.minsk.pendulum.DTO.user.UserDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MessageDtoFull extends AbstractBaseDto {

    @NotBlank
    private String message;

    private UserDto user;

    private List<String> imgKeys;

    private List<byte[]> images;

    public MessageDtoFull() {
    }

    public MessageDtoFull(String message, UserDto user, Date date, List<String> imgKeys, List<byte[]> images) {
        this.message = message;
        this.user = user;
        this.imgKeys = imgKeys;
        this.images = images;
    }
}
