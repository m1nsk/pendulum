package com.minsk.pendulum.repository.dataJpa;

import com.minsk.pendulum.model.ImageEntity;
import com.minsk.pendulum.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DataJpaImageRepositoryImpl implements ImageRepository {

    @Autowired
    private CrudImageRepository crudImageRepository;

    @Override
    public ImageEntity save(ImageEntity image) {
        return crudImageRepository.save(image);
    }

    @Override
    public boolean delete(int id) {
            return crudImageRepository.delete(id) != 0;
    }

    @Override
    public ImageEntity get(int id) {
        return crudImageRepository.findById(id).orElse(null);
    }

    @Override
    public ImageEntity getByHash(String hash) {
        return crudImageRepository.findByHash(hash);
    }
}
