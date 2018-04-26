package com.minsk.pendulum.repository;

import com.minsk.pendulum.model.Channel;

import java.util.List;

public interface ChannelRepository {
    Channel save(Channel channel, int userId);

    // false if not found
    boolean delete(int id, int userId);

    // null if not found
    Channel get(int id, int userId);

    List<Channel> getAll(int userId);
}