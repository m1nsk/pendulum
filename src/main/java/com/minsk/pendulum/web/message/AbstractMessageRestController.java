package com.minsk.pendulum.web.message;

import com.minsk.pendulum.DTO.message.MessageDto;
import com.minsk.pendulum.converter.MessageConverter;
import com.minsk.pendulum.util.exception.NotFoundException;
import com.minsk.pendulum.web.AbstractSecurityController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AbstractMessageRestController extends AbstractSecurityController {

    @Autowired
    private MessageConverter converter;

    public MessageDto create(MessageDto messageDto, int channelId) {
        int userId = authenticationFacade.getUserId();
        return converter.create(messageDto, userId, channelId);
    }

    public void delete(int id) throws NotFoundException {
        int userId = authenticationFacade.getUserId();
        converter.delete(id, userId);
    }

    public MessageDto get(int id) throws NotFoundException {
        return converter.get(id);
    }

    public List<MessageDto> getAll() {
        int userId = authenticationFacade.getUserId();
        return converter.getAll(userId);
    }

}
