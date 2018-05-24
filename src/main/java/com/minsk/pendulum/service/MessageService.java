package com.minsk.pendulum.service;


import com.minsk.pendulum.model.ImageEntity;
import com.minsk.pendulum.model.Message;
import com.minsk.pendulum.util.exception.NotFoundException;

import java.io.IOException;
import java.util.List;

public interface MessageService {

    Message create(Message message, List<ImageEntity> imageEntities, List<byte[]> imageData, int userId, int channelId) throws IOException;

    void delete(int id, int userId) throws NotFoundException;

    Message get(int id) throws NotFoundException;

    Message getCurrentMessageByDevice(int deviceId, int userId) throws NotFoundException;

    List<Message> getAll(int userId);

    List<Message> getAllByChannel(int channelId, int userId);
}