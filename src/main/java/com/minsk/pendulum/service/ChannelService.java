package com.minsk.pendulum.service;


import com.minsk.pendulum.model.Channel;
import com.minsk.pendulum.model.Message;
import com.minsk.pendulum.model.User;
import com.minsk.pendulum.util.exception.NotFoundException;

import java.util.List;

public interface ChannelService {

    Channel create(Channel channel, int userId);

    void delete(int id, int userId) throws NotFoundException;

    Channel get(int id, int userId) throws NotFoundException;

    Channel update(Channel channel, int userId);

    Channel changeOwner(Channel channel, int userId, User newUser);

    Channel setNewMessage(Channel channel, int userId, Message message);

    List<Channel> getAll(int userId);
}