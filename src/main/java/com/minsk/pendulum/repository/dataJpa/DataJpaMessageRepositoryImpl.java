package com.minsk.pendulum.repository.dataJpa;

import com.minsk.pendulum.model.Channel;
import com.minsk.pendulum.model.Device;
import com.minsk.pendulum.model.Message;
import com.minsk.pendulum.repository.ChannelRepository;
import com.minsk.pendulum.repository.MessageRepository;
import com.minsk.pendulum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Repository
public class DataJpaMessageRepositoryImpl implements MessageRepository {

    @Autowired
    private CrudMessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CrudChannelRepository channelRepository;

    @Autowired
    private CrudDeviceRepository deviceRepository;

    @Override
    @Transactional
    public Message save(Message message, int userId, int channelId) {
        if (message.isNew()) {
            Message messageToSave;
            Channel channel = channelRepository.findById(channelId).orElse(null);
            if (channel == null && channel.getUser().getId() != userId) {
                return null;
            }
            message.setUser(userRepository.get(userId));
            message.setChannel(channel);
            messageToSave = messageRepository.save(message);
            channel.setMessage(message);
            return messageToSave;
        }
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return messageRepository.delete(id, userId) != 0;
    }

    @Override
    public Message get(int id) {
        return messageRepository.findById(id).orElse(null);
    }

    @Override
    public Message getCurrentMessageByDevice(int deviceId, int userId) {
        Device device = deviceRepository.getOne(deviceId);
        List<Channel> channels = device.getChannels();
        Message currentMessage = channels.stream()
                .map(item -> item.getMessage())
                .filter(item -> item != null)
                .min(Comparator.comparing(Message::getDate)).orElse(null);
        return currentMessage;
    }

    @Override
    public List<Message> getAll(int userId) {
        return messageRepository.getAll(userId);
    }

    @Override
    public List<Message> getAllByChannel(int channelId, int userId) {
        Channel channel =  channelRepository.findById(channelId).orElse(null);
        if (channel == null || channel.getUser().getId() != userId) {
            return null;
        }
        return channel.getMessages();
    }
}
