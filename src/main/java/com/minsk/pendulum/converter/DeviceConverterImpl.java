package com.minsk.pendulum.converter;

import com.minsk.pendulum.DTO.DtoUtils;
import com.minsk.pendulum.DTO.device.DeviceDto;
import com.minsk.pendulum.model.Device;
import com.minsk.pendulum.service.DeviceService;
import com.minsk.pendulum.util.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.minsk.pendulum.util.ValidationUtil.checkNotFoundWithId;

@Service
@Transactional
public class DeviceConverterImpl {

    private DeviceService service;

    private DtoUtils dtoUtils;

    public DeviceConverterImpl(DeviceService service, DtoUtils dtoUtils) {
        this.service = service;
        this.dtoUtils = dtoUtils;
    }

    public DeviceDto create(DeviceDto deviceDto, int userId) {
        Device device = dtoUtils.convertToEntity(deviceDto);
        return dtoUtils.convertToDto(service.create(device, userId));
    }
    
    public void delete(int id, int userId) {
        service.delete(id, userId);
    }

    public DeviceDto get(int id, int userId) {
        return dtoUtils.convertToDto(service.get(id, userId));
    }

    @Transactional
    public DeviceDto update(DeviceDto deviceDto, int userId) {
        Device device = dtoUtils.convertToEntity(deviceDto);
        device = service.create(device, userId);
        return dtoUtils.convertToDto(device);
    }

    public List<DeviceDto> getAll(int userId) {
        return service.getAll(userId).stream().map(device -> dtoUtils.convertToDto(device))
                .collect(Collectors.toList());
    }

    public List<DeviceDto> getAllByChannel(int channelId, int userId) {
        List<Device> devices = checkNotFoundWithId(service.getAllByChannel(channelId, userId), channelId);
        return devices.stream().map(device -> dtoUtils.convertToDto(device))
                .collect(Collectors.toList());
    }
}