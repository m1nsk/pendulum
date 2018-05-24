package com.minsk.pendulum.service;


import com.minsk.pendulum.model.ImageEntity;
import com.minsk.pendulum.util.exception.NotFoundException;

import java.util.List;

public interface ImageEntityService {

    List<ImageEntity> create(List<ImageEntity> imageEntityList, int messageId);

    boolean delete(int id) throws NotFoundException;

    ImageEntity get(int id) throws NotFoundException;

    ImageEntity getByHash(String hash) throws NotFoundException;

}