package com.minsk.pendulum.DTO.user;

import com.minsk.pendulum.DTO.AbstractBaseDto;
import com.minsk.pendulum.model.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class UserDto extends AbstractBaseDto {
    private String name;

    private String email;

    private Date registered;

    private Set<Role> roles;
}
