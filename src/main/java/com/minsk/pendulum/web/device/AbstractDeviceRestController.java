package com.minsk.pendulum.web.device;

import com.minsk.pendulum.AuthorizedUser;
import com.minsk.pendulum.model.Device;
import com.minsk.pendulum.service.DeviceService;
import com.minsk.pendulum.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.minsk.pendulum.util.ValidationUtil.assureIdConsistent;
import static com.minsk.pendulum.util.ValidationUtil.checkNew;

public class AbstractDeviceRestController {

    @Autowired
    private DeviceService service;

    public Device create(Device device) {
        int userId =AuthorizedUser.id();
        checkNew(device);
        return service.create(device, userId);
    }

    public Device update(Device device, int id) {
        int userId = AuthorizedUser.id();
        assureIdConsistent(device, id);
        return service.create(device, userId);
    }

    public void delete(int id) throws NotFoundException {
        int userId = AuthorizedUser.id();
        service.delete(id, userId);
    }

    public Device get(int id) throws NotFoundException {
        int userId = AuthorizedUser.id();
        return service.get(id, userId);
    }

    public List<Device> getAll(int userId) {
        return service.getAll(userId);
    }
}
