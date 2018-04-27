package com.minsk.pendulum.repository.dataJpa;

import com.minsk.pendulum.model.Channel;
import com.minsk.pendulum.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DataJpaChannelRepositoryImpl implements ChannelRepository {

    @Autowired
    private CrudChannelRepository crudDeviceRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    @Transactional
    public Channel save(Channel channel, int userId) {
        if (!channel.isNew() && get(channel.getId(), userId) == null) {
            return null;
        }
        channel.setUser(crudUserRepository.getOne(userId));
        return crudDeviceRepository.save(channel);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudDeviceRepository.delete(id, userId) != 0;
    }

    @Override
    public Channel get(int id, int userId) {
        return crudDeviceRepository.findById(id).filter(channel -> channel.getUser().getId() == userId).orElse(null);
    }

    @Override
    public List<Channel> getAll(int userId) {
        return crudDeviceRepository.getAll(userId);
    }
}
