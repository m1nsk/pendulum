package com.minsk.pendulum.service;

import com.minsk.pendulum.DTO.DtoUtils;
import com.minsk.pendulum.DTO.channel.ChannelDto;
import com.minsk.pendulum.model.Channel;
import com.minsk.pendulum.model.Message;
import com.minsk.pendulum.model.User;
import com.minsk.pendulum.repository.ChannelRepository;
import com.minsk.pendulum.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

import static com.minsk.pendulum.util.ValidationUtil.assureIdConsistent;
import static com.minsk.pendulum.util.ValidationUtil.checkNew;
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
    public void addDevice(int channelId, int deviceId) {
        repository.addDevice(channelId, deviceId);
    }

    @Override
    public List<Channel> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    @Transactional
    public List<Channel> getAllByDevice(int deviceId, int userId) {
        return repository.getAllByDevice(deviceId, userId);
    }
}