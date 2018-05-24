package com.minsk.pendulum.converter;

import com.minsk.pendulum.DTO.DtoUtils;
import com.minsk.pendulum.DTO.message.MessageDto;
import com.minsk.pendulum.DTO.message.MessageDtoFull;
import com.minsk.pendulum.model.ImageEntity;
import com.minsk.pendulum.model.Message;
import com.minsk.pendulum.service.ImageEntityService;
import com.minsk.pendulum.service.MessageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MessageConverterImpl {

    private MessageService messageService;

    private ImageEntityService imageEntityService;

    private DtoUtils dtoUtils;

    public MessageConverterImpl(MessageService messageService, ImageEntityService imageEntityService, DtoUtils dtoUtils) {
        this.messageService = messageService;
        this.imageEntityService = imageEntityService;
        this.dtoUtils = dtoUtils;
    }

    public MessageDto create(MessageDtoFull messageDto, int userId, int channelId) throws IOException {
        Message message = dtoUtils.convertToEntity(messageDto);
        List<ImageEntity> imageEntities = messageDto
                .getImgKeys()
                .stream()
                .map(item -> new ImageEntity(item, item))
                .collect(Collectors.toList());
        List<byte[]> images = messageDto.getImages();
        MessageDto resultMessage = dtoUtils.convertToDto(messageService.create(message, imageEntities, images, userId, channelId));
        return resultMessage;
    }

    public void delete(int id, int userId) {
        messageService.delete(id, userId);
    }

    public MessageDto get(int id) {
        return dtoUtils.convertToDto(messageService.get(id));
    }

    public MessageDto getCurrentMessageByDevice(int deviceId, int userId) {
        return dtoUtils.convertToDto(messageService.getCurrentMessageByDevice(deviceId, userId));
    }

    public List<MessageDto> getAll(int userId) {
        return messageService.getAll(userId).stream().map(message -> dtoUtils.convertToDto(message)).collect(Collectors.toList());
    }

    public List<MessageDto> getAllByChannel(int channelId, int userId) {
        return messageService.getAllByChannel(channelId, userId).stream().map(message -> dtoUtils.convertToDto(message)).collect(Collectors.toList());
    }
}