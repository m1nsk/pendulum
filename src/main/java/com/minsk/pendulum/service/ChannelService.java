package com.minsk.pendulum.service;


import com.minsk.pendulum.model.Channel;
import com.minsk.pendulum.util.exception.NotFoundException;

import java.util.List;

public interface ChannelService {

    Channel create(Channel channel, int userId);

    void delete(int id, int userId) throws NotFoundException;

    Channel get(int id, int userId) throws NotFoundException;

    Channel update(Channel channel, int userId);

    void addDevice(int channelId, int deviceId);

    List<Channel> getAll(int userId);

    List<Channel> getAllByDevice(int deviceId, int userId);
}