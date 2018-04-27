package com.minsk.pendulum.service;


import com.minsk.pendulum.model.Device;
import com.minsk.pendulum.util.exception.NotFoundException;

import java.util.List;

public interface DeviceService {

    Device create(Device device, int userId);

    void delete(int id, int userId) throws NotFoundException;

    Device get(int id, int userId) throws NotFoundException;

    Device update(Device device, int userId);

    List<Device> getAll(int userId);
}