package com.minsk.pendulum.repository.dataJpa;

import com.minsk.pendulum.model.Message;
import com.minsk.pendulum.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DataJpaMessageRepositoryImpl implements MessageRepository {

    @Autowired
    private CrudMessageRepository crudMessageRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    @Transactional
    public Message save(Message device, int userId) {
        if (!device.isNew()) {
            return null;
        }
        device.setUser(crudUserRepository.getOne(userId));
        return crudMessageRepository.save(device);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudMessageRepository.delete(id, userId) != 0;
    }

    @Override
    public Message get(int id) {
        return crudMessageRepository.findById(id).orElse(null);
    }

    @Override
    public List<Message> getAll(int userId) {
        return crudMessageRepository.getAll(userId);
    }
}
