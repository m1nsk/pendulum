package com.minsk.pendulum.util;


import com.minsk.pendulum.DTO.AbstractBaseDto;
import com.minsk.pendulum.util.exception.NotFoundException;

public class ValidationUtil {

    private ValidationUtil() {
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static void checkNew(AbstractBaseDto dto) {
        if (!dto.isNew()) {
            throw new IllegalArgumentException(dto + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(AbstractBaseDto dto, int id) {
        if (dto.isNew()) {
            dto.setId(id);
        } else if (dto.getId() != id) {
            throw new IllegalArgumentException(dto + " must be with id=" + id);
        }
    }
}