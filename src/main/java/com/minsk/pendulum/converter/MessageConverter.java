package com.minsk.pendulum.converter;


import com.minsk.pendulum.DTO.message.MessageDto;
import com.minsk.pendulum.util.exception.NotFoundException;

import java.util.List;

public interface MessageConverter {

    MessageDto create(MessageDto messageDto, int userId, int channelId);

    void delete(int id, int userId) throws NotFoundException;

    MessageDto get(int id) throws NotFoundException;

    List<MessageDto> getAll(int userId);
}