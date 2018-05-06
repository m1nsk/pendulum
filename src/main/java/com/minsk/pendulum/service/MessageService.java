package com.minsk.pendulum.service;


import com.minsk.pendulum.model.Message;
import com.minsk.pendulum.model.Message;
import com.minsk.pendulum.util.exception.NotFoundException;

import java.util.List;

public interface MessageService {

    Message create(Message message, int userId, int channelId);

    void delete(int id, int userId) throws NotFoundException;

    Message get(int id) throws NotFoundException;

    List<Message> getAll(int userId);
}