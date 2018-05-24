package com.minsk.pendulum.service.Impl;

import com.minsk.pendulum.model.ImageEntity;
import com.minsk.pendulum.model.Message;
import com.minsk.pendulum.repository.ImageRepository;
import com.minsk.pendulum.repository.MessageRepository;
import com.minsk.pendulum.repository.dataJpa.CrudMessageRepository;
import com.minsk.pendulum.service.ImageEntityService;
import com.minsk.pendulum.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageEntityServiceImpl implements ImageEntityService {

    private ImageRepository imageRepository;

    private CrudMessageRepository messageRepository;

    @Autowired
    public ImageEntityServiceImpl(ImageRepository imageRepository, CrudMessageRepository messageRepository) {
        this.imageRepository = imageRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    @Transactional
    public List<ImageEntity> create(List<ImageEntity> imageEntityList, int messageId) {
        Message message = messageRepository.getOne(messageId);
        for (int i = 0; i < imageEntityList.size(); i++) {
            imageEntityList.get(i).addMessage(message);
            imageEntityList.set(i, imageRepository.save(imageEntityList.get(i)));
        }
        return imageEntityList;
    }

    @Override
    public boolean delete(int id) throws NotFoundException {
        return imageRepository.delete(id);
    }

    @Override
    public ImageEntity get(int id) throws NotFoundException {
        return imageRepository.get(id);
    }

    @Override
    public ImageEntity getByHash(String hash) throws NotFoundException {
        return imageRepository.getByHash(hash);
    }
}