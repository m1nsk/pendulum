package com.minsk.pendulum.DTO.image;

import com.minsk.pendulum.DTO.AbstractBaseDto;
import com.minsk.pendulum.DTO.user.UserDto;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ImageEntityDto extends AbstractBaseDto {

    private String hash;

    private String path;

}
