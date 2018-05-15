package com.minsk.pendulum.web.device;

import com.minsk.pendulum.DTO.device.DeviceDto;
import com.minsk.pendulum.converter.DeviceConverter;
import com.minsk.pendulum.util.exception.NotFoundException;
import com.minsk.pendulum.web.AbstractSecurityController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.minsk.pendulum.util.ValidationUtil.assureIdConsistent;
import static com.minsk.pendulum.util.ValidationUtil.checkNew;

public class AbstractDeviceRestController extends AbstractSecurityController {

    @Autowired
    private DeviceConverter converter;

    public DeviceDto create(DeviceDto deviceDto) {
        checkNew(deviceDto);
        int userId =authenticationFacade.getUserId();
        return converter.create(deviceDto, userId);
    }

    public DeviceDto update(DeviceDto deviceDto, int id) {
        assureIdConsistent(deviceDto, id);
        int userId = authenticationFacade.getUserId();
        return converter.update(deviceDto, userId);
    }

    public void delete(int id) throws NotFoundException {
        int userId = authenticationFacade.getUserId();
        converter.delete(id, userId);
    }

    public DeviceDto get(int id) throws NotFoundException {
        int userId = authenticationFacade.getUserId();
        return converter.get(id, userId);
    }

    public List<DeviceDto> getAll() {
        int userId = authenticationFacade.getUserId();
        return converter.getAll(userId);
    }

    public List<DeviceDto> getAllByChannel(int channelId) {
        int userId = authenticationFacade.getUserId();
        return converter.getAllByChannel(channelId, userId);
    }
}
