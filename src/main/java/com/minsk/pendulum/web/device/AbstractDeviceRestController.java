package com.minsk.pendulum.web.device;

import com.minsk.pendulum.AuthorizedUser;
import com.minsk.pendulum.DTO.device.DeviceDto;
import com.minsk.pendulum.DTO.DtoUtils;
import com.minsk.pendulum.model.Device;
import com.minsk.pendulum.service.DeviceService;
import com.minsk.pendulum.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static com.minsk.pendulum.util.ValidationUtil.assureIdConsistent;
import static com.minsk.pendulum.util.ValidationUtil.checkNew;

public class AbstractDeviceRestController {

    @Autowired
    private DeviceService service;

    @Autowired
    private DtoUtils dtoUtils;

    public DeviceDto create(DeviceDto deviceDto) {
        int userId =AuthorizedUser.id();
        Device device = dtoUtils.convertToEntity(deviceDto);
        checkNew(device);
        device = service.create(device, userId);
        return dtoUtils.convertToDto(device);
    }

    public DeviceDto update(DeviceDto deviceDto, int id) {
        int userId = AuthorizedUser.id();
        Device device = dtoUtils.convertToEntity(deviceDto);
        assureIdConsistent(device, id);
        device = service.create(device, userId);
        return dtoUtils.convertToDto(device);
    }

    public void delete(int id) throws NotFoundException {
        int userId = AuthorizedUser.id();
        service.delete(id, userId);
    }

    public DeviceDto get(int id) throws NotFoundException {
        int userId = AuthorizedUser.id();
        return dtoUtils.convertToDto(service.get(id, userId));
    }

    public List<DeviceDto> getAll() {
        int userId = AuthorizedUser.id();
        List<Device> devices = service.getAll(userId);
        return devices.stream().map(device -> dtoUtils.convertToDto(device))
                .collect(Collectors.toList());
    }
}
