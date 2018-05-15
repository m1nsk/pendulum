package com.minsk.pendulum.converter;

import com.minsk.pendulum.DTO.DtoUtils;
import com.minsk.pendulum.DTO.message.MessageDto;
import com.minsk.pendulum.model.Message;
import com.minsk.pendulum.service.MessageService;
import com.minsk.pendulum.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

import static com.minsk.pendulum.util.ValidationUtil.checkNotFoundWithId;

@Service
@Transactional
public class MessageConverterImpl implements MessageConverter {

    private MessageService service;

    private DtoUtils dtoUtils;

    @Autowired
    public MessageConverterImpl(MessageService service, DtoUtils dtoUtils) {
        this.service = service;
        this.dtoUtils = dtoUtils;
    }

    @Override
    public MessageDto create(MessageDto messageDto, int userId, int channelId) {
        Message message = dtoUtils.convertToEntity(messageDto);
        return dtoUtils.convertToDto(service.create(message, userId, channelId));
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        service.delete(id, userId);
    }

    @Override
    public MessageDto get(int id) throws NotFoundException {
        return dtoUtils.convertToDto(checkNotFoundWithId(service.get(id), id));
    }

    @Override
    public List<MessageDto> getAll(int userId) {
        return service.getAll(userId).stream().map(message -> dtoUtils.convertToDto(message)).collect(Collectors.toList());
    }

    @Override
    public List<MessageDto> getAllByChannel(int channelId, int userId) {
        return service.getAllByChannel(channelId, userId).stream().map(message -> dtoUtils.convertToDto(message)).collect(Collectors.toList());
    }
}