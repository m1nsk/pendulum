package com.minsk.pendulum.service;

import com.minsk.pendulum.model.Device;
import com.minsk.pendulum.repository.DeviceRepository;
import com.minsk.pendulum.repository.DeviceRepository;
import com.minsk.pendulum.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.minsk.pendulum.util.ValidationUtil.checkNotFound;
import static com.minsk.pendulum.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DeviceServiceImpl implements DeviceService {

    private DeviceRepository repository;

    @Autowired
    public DeviceServiceImpl(DeviceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Device create(Device device, int userId) {
        Assert.notNull(device, "device must not be null");
        return repository.save(device, userId);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public Device get(int id, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public Device update(Device device, int userId) {
        return checkNotFoundWithId(repository.save(device, userId), device.getId());
    }

    @Override
    public List<Device> getAll(int userId) {
        return repository.getAll(userId);
    }
}