package com.minsk.pendulum.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public abstract class AbstractBaseDto {

    private Integer id;

    public boolean isNew() {
        if (id == null)
            return true;
        return false;
    }
}
