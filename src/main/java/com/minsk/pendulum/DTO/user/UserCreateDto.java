package com.minsk.pendulum.DTO.user;

import com.minsk.pendulum.DTO.AbstractBaseDto;
import com.minsk.pendulum.model.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class UserCreateDto extends AbstractBaseDto {
    @NotNull
    private String name;
    @NotNull
    private String password;
    @NotNull
    private String email;
    @NotNull
    private Set<Role> roles;
}
