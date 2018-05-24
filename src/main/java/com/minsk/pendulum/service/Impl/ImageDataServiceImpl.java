package com.minsk.pendulum.service.Impl;

import com.minsk.pendulum.model.ImageEntity;
import com.minsk.pendulum.service.ImageDataService;
import com.minsk.pendulum.util.exception.NotFoundException;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ImageDataServiceImpl implements ImageDataService {

    private static final Logger log = Logger.getLogger(ImageDataServiceImpl.class.getName());

    @Value("${store.path}")
    private static String SAVE_PATH = "/home/minsk/Изображения/New/";

    @Override
    @Transactional
    public List<String> create(List<byte[]> imageData, List<String> pathList) throws IOException {
       try {
           for (int i = 0; i < pathList.size(); i++) {
               File file = new File(SAVE_PATH + pathList.get(i));
               if (!file.exists()) {
                   FileUtils.writeByteArrayToFile(file, imageData.get(i));
               } else {
                   pathList.remove(i);
               }
           }
           return pathList;
       } catch (Exception e){
           delete(pathList);
           throw e;
       }

    }

    @Override
    public void delete(List<String> pathList) throws NotFoundException {
        for (int i = 0; i < pathList.size(); i++) {
            File file = new File(SAVE_PATH + pathList.get(i));
            if (!file.exists() && file.delete()) {
                log.log(Level.INFO, pathList.get(i) + " successfully deleted");
            } else {
                log.log(Level.WARNING, pathList.get(i) + " no access");
            }
        }
    }

    @Override
    public ImageEntity get(String path) throws NotFoundException {
        return null;
    }
}