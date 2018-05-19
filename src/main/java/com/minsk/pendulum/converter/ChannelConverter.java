package com.minsk.pendulum.converter;


import com.minsk.pendulum.DTO.channel.ChannelDto;
import com.minsk.pendulum.util.exception.NotFoundException;

import java.util.List;

public interface ChannelConverter {

    ChannelDto create(ChannelDto channel, int userId);

    void delete(int id, int userId) throws NotFoundException;

    ChannelDto get(int id, int userId) throws NotFoundException;

    ChannelDto update(ChannelDto channel, int userId);

    void addDevice(int channelId, int deviceId);

    List<ChannelDto> getAll(int userId);

    List<ChannelDto> getAllByDevice(int deviceId, int userId);
}