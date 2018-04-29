package com.minsk.pendulum.web.message;

import com.minsk.pendulum.AuthorizedUser;
import com.minsk.pendulum.model.Message;
import com.minsk.pendulum.service.MessageService;
import com.minsk.pendulum.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.minsk.pendulum.util.ValidationUtil.assureIdConsistent;
import static com.minsk.pendulum.util.ValidationUtil.checkNew;

public class AbstractMessageRestController {

    @Autowired
    private MessageService service;

    public Message create(Message message) {
        int userId =AuthorizedUser.id();
        return service.create(message, userId);
    }

    public void delete(int id) throws NotFoundException {
        int userId = AuthorizedUser.id();
        service.delete(id, userId);
    }

    public Message get(int id) throws NotFoundException {
        return service.get(id);
    }

    public List<Message> getAll(int userId) {
        return service.getAll(userId);
    }
}
