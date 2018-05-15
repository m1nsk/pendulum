package com.minsk.pendulum.repository.dataJpa;

import com.minsk.pendulum.model.Channel;
import com.minsk.pendulum.model.Device;
import com.minsk.pendulum.model.User;
import com.minsk.pendulum.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DataJpaChannelRepositoryImpl implements ChannelRepository {

    @Autowired
    private CrudChannelRepository crudChannelRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Autowired
    private CrudDeviceRepository crudDeviceRepository;

    @Override
    @Transactional
    public Channel save(Channel channel, int userId) {
        if (channel.isNew()) {
            channel.setUser(crudUserRepository.getOne(userId));
            return crudChannelRepository.save(channel);
        }
        if (get(channel.getId(), userId) == null) {
            return null;
        }
        Channel channelToUpdate = crudChannelRepository.getOne(channel.getId());
        channelToUpdate.channelUpdate(channel);
        return crudChannelRepository.save(channelToUpdate);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudChannelRepository.delete(id, userId) != 0;
    }

    @Override
    public Channel get(int id, int userId) {
        return crudChannelRepository.findById(id).filter(channel -> channel.getUser().getId() == userId).orElse(null);
    }

    @Override
    public List<Channel> getAll(int userId) {
        return crudChannelRepository.getAll(userId);
    }

    @Override
    public List<Channel> getAllByDevice(int deviceId, int userID) {
        Device device = crudDeviceRepository.findById(deviceId).orElse(null);
        if (device == null && !device.getUser().getId().equals(userID)){
            return null;
        }
        List<Channel> result = device.getChannels();
        return result;
    }
}
