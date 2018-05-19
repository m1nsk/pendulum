package com.minsk.pendulum.converter;


import com.minsk.pendulum.DTO.message.MessageDto;
import com.minsk.pendulum.util.exception.NotFoundException;

import java.util.List;

public interface MessageConverter {

    MessageDto create(MessageDto messageDto, int userId, int channelId);

    void delete(int id, int userId);

    MessageDto get(int id);

    MessageDto getCurrentMessageByDevice(int deviceId, int userId);

    List<MessageDto> getAll(int userId);

    List<MessageDto> getAllByChannel(int channelId, int userId);
}