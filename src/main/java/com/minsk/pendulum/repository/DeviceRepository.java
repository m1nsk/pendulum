package com.minsk.pendulum.repository;

import com.minsk.pendulum.model.Device;

import java.util.List;

public interface DeviceRepository {
    Device save(Device device, int userId);

    // false if not found
    boolean delete(int id, int userId);

    // null if not found
    Device get(int id, int userId);

    List<Device> getAll(int userId);

    List<Device> getAllByChannel(int channelId, int userID);
}