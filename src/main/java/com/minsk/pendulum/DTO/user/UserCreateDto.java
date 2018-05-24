package com.minsk.pendulum.DTO.user;

import com.minsk.pendulum.DTO.AbstractBaseDto;
import com.minsk.pendulum.model.Role;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
public class UserCreateDto extends AbstractBaseDto {
    @NotBlank
    @Length(min = 3)
    private String name;
    @NotBlank
    @Length(min = 6)
    private String password;
    @NotBlank
    @Email
    private String email;

    private Set<Role> roles;
}
