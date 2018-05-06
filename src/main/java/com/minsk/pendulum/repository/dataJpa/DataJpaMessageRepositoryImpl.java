package com.minsk.pendulum.repository.dataJpa;

import com.minsk.pendulum.model.Channel;
import com.minsk.pendulum.model.Message;
import com.minsk.pendulum.repository.ChannelRepository;
import com.minsk.pendulum.repository.MessageRepository;
import com.minsk.pendulum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DataJpaMessageRepositoryImpl implements MessageRepository {

    @Autowired
    private CrudMessageRepository crudMessageRepository;

    @Autowired
    private UserRepository crudUserRepository;

    @Autowired
    private ChannelRepository dataJpaChannelRepository;

    @Override
    @Transactional
    public Message save(Message message, int userId, int channelId) {
        if (!message.isNew()) {
            return null;
        }
        Channel channel = dataJpaChannelRepository.get(channelId, userId);
        if (channel == null) {
            return null;
        }
        message.setUser(crudUserRepository.get(userId));
        message.setChannel(channel);
        Message message_result = crudMessageRepository.save(message);
        channel.setMessage(message_result);
        return message;
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
