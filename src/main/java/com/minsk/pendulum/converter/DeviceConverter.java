package com.minsk.pendulum.converter;


import com.minsk.pendulum.DTO.device.DeviceDto;
import com.minsk.pendulum.util.exception.NotFoundException;

import java.util.List;

public interface DeviceConverter {

    DeviceDto create(DeviceDto device, int userId);

    void delete(int id, int userId) throws NotFoundException;

    DeviceDto get(int id, int userId) throws NotFoundException;

    DeviceDto update(DeviceDto device, int userId);

    List<DeviceDto> getAll(int userId);

    List<DeviceDto> getAllByChannel(int channelId, int userID);
}