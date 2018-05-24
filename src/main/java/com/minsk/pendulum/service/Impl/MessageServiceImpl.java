package com.minsk.pendulum.service.Impl;

import com.minsk.pendulum.model.ImageEntity;
import com.minsk.pendulum.model.Message;
import com.minsk.pendulum.repository.MessageRepository;
import com.minsk.pendulum.service.ImageDataService;
import com.minsk.pendulum.service.ImageEntityService;
import com.minsk.pendulum.service.MessageService;
import com.minsk.pendulum.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.minsk.pendulum.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;

    private ImageEntityService imageEntityService;

    private ImageDataService imageDataService;

    @Autowired
    public MessageServiceImpl(MessageRepository repository, ImageEntityService imageEntityService, ImageDataService imageDataService) {
        this.messageRepository = repository;
        this.imageEntityService = imageEntityService;
        this.imageDataService = imageDataService;
    }

//    @Override
//    public Message create(Message message, int userId, int channelId) {
//        Assert.notNull(message, "Message must not be null");
//        return messageRepository.save(message, userId, channelId);
//    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Message create(Message message, List<ImageEntity> imageEntities, List<byte[]> imageData, int userId, int channelId) throws IOException {
        List<String> filePaths = imageEntities.stream().map(item -> item.getFilePath()).collect(Collectors.toList());
        try {
            Assert.notNull(message, "Message must not be null");
            message = messageRepository.save(message, userId, channelId);
            imageEntities = imageEntityService.create(imageEntities, message.getId());
            filePaths = imageDataService.create(imageData, filePaths);
            return message;
        } catch (Exception e) {
            imageDataService.delete(filePaths);
            throw e;
        }
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        checkNotFoundWithId(messageRepository.delete(id, userId), id);
    }

    @Override
    public Message get(int id) throws NotFoundException {
        return checkNotFoundWithId(messageRepository.get(id), id);
    }

    @Override
    public Message getCurrentMessageByDevice(int deviceId, int userId) {
        return checkNotFoundWithId(messageRepository.getCurrentMessageByDevice(deviceId, userId), deviceId);
    }

    @Override
    public List<Message> getAll(int userId) {
        return messageRepository.getAll(userId);
    }

    @Override
    public List<Message> getAllByChannel(int channelId, int userId) {
        return messageRepository.getAllByChannel(channelId, userId);
    }
}