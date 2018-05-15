package com.minsk.pendulum.converter;


import com.minsk.pendulum.model.User;
import com.minsk.pendulum.util.exception.NotFoundException;

import java.util.List;

public interface UserConverter {

    User create(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    void update(User user);

    List<User> getAll();
}