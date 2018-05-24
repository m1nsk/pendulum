package com.minsk.pendulum.converter;

import com.minsk.pendulum.DTO.DtoUtils;
import com.minsk.pendulum.DTO.channel.ChannelDto;
import com.minsk.pendulum.model.Channel;
import com.minsk.pendulum.service.ChannelService;
import com.minsk.pendulum.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ChannelConverterImpl {

    private ChannelService service;

    private DtoUtils dtoUtils;

    @Autowired
    public ChannelConverterImpl(ChannelService service, DtoUtils dtoUtils) {
        this.service = service;
        this.dtoUtils = dtoUtils;
    }

    public ChannelDto create(ChannelDto channelDto, int userId) {
        Channel channel = dtoUtils.convertToEntity(channelDto);
        return dtoUtils.convertToDto(service.create(channel, userId));
    }

    public void delete(int id, int userId) {
        service.delete(id, userId);
    }

    public ChannelDto get(int id, int userId) {
        return dtoUtils.convertToDto(service.get(id, userId));
    }

    public ChannelDto update(ChannelDto channelDto, int userId) {
            Channel channel = dtoUtils.convertToEntity(channelDto);
            channel = service.create(channel, userId);
            return dtoUtils.convertToDto(channel);
    }

    public void addDevice(int channelId, int deviceId) {
        service.addDevice(channelId, deviceId);
    }

    public List<ChannelDto> getAll(int userId) {
        return service.getAll(userId).stream().map(channel -> dtoUtils.convertToDto(channel))
                .collect(Collectors.toList());
    }

    public List<ChannelDto> getAllByDevice(int deviceId, int userId) {
        List<Channel> channels = service.getAllByDevice(deviceId, userId);
        return channels.stream().map(channel -> dtoUtils.convertToDto(channel))
                .collect(Collectors.toList());
    }
}