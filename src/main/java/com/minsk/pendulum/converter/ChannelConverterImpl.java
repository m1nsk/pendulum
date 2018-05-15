package com.minsk.pendulum.converter;

import com.minsk.pendulum.DTO.DtoUtils;
import com.minsk.pendulum.DTO.channel.ChannelDto;
import com.minsk.pendulum.model.Channel;
import com.minsk.pendulum.service.ChannelService;
import com.minsk.pendulum.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

import static com.minsk.pendulum.util.ValidationUtil.checkNotFoundWithId;

@Service
public class ChannelConverterImpl implements ChannelConverter {

    private ChannelService service;

    private DtoUtils dtoUtils;

    @Autowired
    public ChannelConverterImpl(ChannelService service, DtoUtils dtoUtils) {
        this.service = service;
        this.dtoUtils = dtoUtils;
    }
    
    @Override
    public ChannelDto create(ChannelDto channelDto, int userId) {
        Channel channel = dtoUtils.convertToEntity(channelDto);
        Assert.notNull(channel, "channel must not be null");
        return dtoUtils.convertToDto(service.create(channel, userId));
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        service.delete(id, userId);
    }

    @Override
    public ChannelDto get(int id, int userId) throws NotFoundException {
        return dtoUtils.convertToDto(service.get(id, userId));
    }

    @Override
    public ChannelDto update(ChannelDto channelDto, int userId) {
            Channel channel = dtoUtils.convertToEntity(channelDto);
            channel = checkNotFoundWithId(service.create(channel, userId), channel.getId());
            return dtoUtils.convertToDto(channel);
    }

    @Override
    public List<ChannelDto> getAll(int userId) {
        return service.getAll(userId).stream().map(channel -> dtoUtils.convertToDto(channel))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ChannelDto> getAllByDevice(int deviceId, int userId) {
        List<Channel> channels = checkNotFoundWithId(service.getAllByDevice(deviceId, userId), deviceId);
        return channels.stream().map(channel -> dtoUtils.convertToDto(channel))
                .collect(Collectors.toList());
    }
}