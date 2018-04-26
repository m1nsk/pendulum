package com.minsk.pendulum.service;

import com.minsk.pendulum.model.Channel;
import com.minsk.pendulum.repository.ChannelRepository;
import com.minsk.pendulum.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.minsk.pendulum.util.ValidationUtil.checkNotFoundWithId;

@Service
public class ChannelServiceImpl implements ChannelService {

    private ChannelRepository repository;

    @Autowired
    public ChannelServiceImpl(ChannelRepository repository) {
        this.repository = repository;
    }

    @Override
    public Channel create(Channel channel, int userId) {
        Assert.notNull(channel, "channel must not be null");
        return repository.save(channel, userId);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public Channel get(int id, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public Channel update(Channel channel, int userId) {
        return checkNotFoundWithId(repository.save(channel, userId), channel.getId());
    }

    @Override
    public List<Channel> getAll(int userId) {
        return repository.getAll(userId);
    }
}