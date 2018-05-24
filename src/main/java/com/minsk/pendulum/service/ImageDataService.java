package com.minsk.pendulum.service;


import com.minsk.pendulum.model.ImageEntity;
import com.minsk.pendulum.util.exception.NotFoundException;

import java.io.IOException;
import java.util.List;

public interface ImageDataService {

    List<String> create(List<byte[]> imageData, List<String> path) throws IOException;

    void delete(List<String> pathList);

    ImageEntity get(String path) throws NotFoundException;

}