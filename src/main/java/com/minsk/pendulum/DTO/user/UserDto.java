package com.minsk.pendulum.DTO.user;

import com.minsk.pendulum.model.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class UserDto {
    @NotNull
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private Date registered;
    @NotNull
    private Set<Role> roles;
}
