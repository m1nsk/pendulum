package com.minsk.pendulum.service;

import com.minsk.pendulum.model.Message;
import com.minsk.pendulum.repository.MessageRepository;
import com.minsk.pendulum.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.minsk.pendulum.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageRepository repository;

    @Autowired
    public MessageServiceImpl(MessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public Message create(Message message, int userId, int channelId) {
        Assert.notNull(message, "Message must not be null");
        return repository.save(message, userId, channelId);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public Message get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<Message> getAll(int userId) {
        return repository.getAll(userId);
    }
}