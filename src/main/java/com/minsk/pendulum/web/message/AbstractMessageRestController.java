package com.minsk.pendulum.web.message;

import com.minsk.pendulum.DTO.DtoUtils;
import com.minsk.pendulum.DTO.message.MessageDto;
import com.minsk.pendulum.components.IAuthenticationFacade;
import com.minsk.pendulum.model.Message;
import com.minsk.pendulum.service.MessageService;
import com.minsk.pendulum.util.exception.NotFoundException;
import com.minsk.pendulum.web.AbstractSecurityController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class AbstractMessageRestController extends AbstractSecurityController {

    @Autowired
    private MessageService service;

    @Autowired
    private DtoUtils dtoUtils;

    public MessageDto create(MessageDto messageDto) {
        int userId = authenticationFacade.getUserId();
        int channelId = messageDto.getChannelId();
        Message message = dtoUtils.convertToEntity(messageDto);
        message = service.create(message, userId, channelId);
        return dtoUtils.convertToDto(message);
    }

    public void delete(int id) throws NotFoundException {
        int userId = authenticationFacade.getUserId();
        service.delete(id, userId);
    }

    public MessageDto get(int id) throws NotFoundException {
        return dtoUtils.convertToDto(service.get(id));
    }

    public List<MessageDto> getAll() {
        int userId = authenticationFacade.getUserId();
        List<Message> messages = service.getAll(userId);
        return messages.stream().map(message -> dtoUtils.convertToDto(message)).collect(Collectors.toList());
    }

}
