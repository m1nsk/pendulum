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
public class UserFullDto extends AbstractBaseDto {
    @NotNull
    private String name;
    @NotNull
    private String password;
    @NotNull
    private String email;
    @NotNull
    private Date registered;
    @NotNull
    private Set<Role> roles;
}
