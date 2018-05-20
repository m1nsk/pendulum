package com.minsk.pendulum.repository;

import com.minsk.pendulum.model.ImageEntity;

public interface ImageRepository {
    ImageEntity save(ImageEntity image);

    // false if not found
    boolean delete(int id);

    // null if not found
    ImageEntity get(int id);
    
    ImageEntity getByHash(int hash);
}