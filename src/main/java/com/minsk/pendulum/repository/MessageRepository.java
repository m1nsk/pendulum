package com.minsk.pendulum.repository;

import com.minsk.pendulum.model.Message;

import java.util.List;

public interface MessageRepository {
    Message save(Message message, int userId, int channelId);

    // false if not found
    boolean delete(int id, int userId);

    // null if not found
    Message get(int id);

    List<Message> getAll(int userId);

    List<Message> getAllByChannel(int channelId, int userId);
}