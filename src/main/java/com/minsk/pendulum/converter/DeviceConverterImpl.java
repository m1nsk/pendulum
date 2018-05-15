package com.minsk.pendulum.converter;

import com.minsk.pendulum.DTO.DtoUtils;
import com.minsk.pendulum.DTO.device.DeviceDto;
import com.minsk.pendulum.model.Device;
import com.minsk.pendulum.service.DeviceService;
import com.minsk.pendulum.util.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

import static com.minsk.pendulum.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DeviceConverterImpl implements DeviceConverter {

    private DeviceService service;

    private DtoUtils dtoUtils;

    public DeviceConverterImpl(DeviceService service, DtoUtils dtoUtils) {
        this.service = service;
        this.dtoUtils = dtoUtils;
    }

    @Override
    public DeviceDto create(DeviceDto deviceDto, int userId) {
        Device device = dtoUtils.convertToEntity(deviceDto);
        Assert.notNull(device, "channel must not be null");
        return dtoUtils.convertToDto(service.create(device, userId));
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        service.delete(id, userId);
    }

    @Override
    public DeviceDto get(int id, int userId) throws NotFoundException {
        return dtoUtils.convertToDto(service.get(id, userId));
    }

    @Override
    @Transactional
    public DeviceDto update(DeviceDto deviceDto, int userId) {
        Device device = dtoUtils.convertToEntity(deviceDto);
        device = checkNotFoundWithId(service.create(device, userId), device.getId());
        return dtoUtils.convertToDto(device);
    }

    @Override
    public List<DeviceDto> getAll(int userId) {
        return service.getAll(userId).stream().map(device -> dtoUtils.convertToDto(device))
                .collect(Collectors.toList());
    }

    @Override
    public List<DeviceDto> getAllByChannel(int channelId, int userId) {
        List<Device> devices = checkNotFoundWithId(service.getAllByChannel(channelId, userId), channelId);
        return devices.stream().map(device -> dtoUtils.convertToDto(device))
                .collect(Collectors.toList());
    }
}