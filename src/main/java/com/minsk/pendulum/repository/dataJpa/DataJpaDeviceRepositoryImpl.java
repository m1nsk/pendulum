package com.minsk.pendulum.repository.dataJpa;

import com.minsk.pendulum.model.Device;
import com.minsk.pendulum.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DataJpaDeviceRepositoryImpl implements DeviceRepository {

    @Autowired
    private CrudDeviceRepository crudDeviceRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    @Transactional
    public Device save(Device device, int userId) {
        if (!device.isNew() && get(device.getId(), userId) == null) {
            return null;
        }
        device.setUser(crudUserRepository.getOne(userId));
        return crudDeviceRepository.save(device);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudDeviceRepository.delete(id, userId) != 0;
    }

    @Override
    public Device get(int id, int userId) {
        return crudDeviceRepository.findById(id).filter(device -> device.getUser().getId() == userId).orElse(null);
    }

    @Override
    public List<Device> getAll(int userId) {
        return crudDeviceRepository.getAll(userId);
    }
}
