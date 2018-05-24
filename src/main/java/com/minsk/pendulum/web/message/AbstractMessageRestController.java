package com.minsk.pendulum.web.message;

import com.minsk.pendulum.DTO.message.MessageDto;
import com.minsk.pendulum.DTO.message.MessageDtoFull;
import com.minsk.pendulum.converter.MessageConverterImpl;
import com.minsk.pendulum.util.ImageUtil;
import com.minsk.pendulum.util.exception.NotFoundException;
import com.minsk.pendulum.web.AbstractSecurityController;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AbstractMessageRestController extends AbstractSecurityController {

    @Autowired
    private MessageConverterImpl converter;

    protected MessageDto uploadMessage(String instructions, String keys, List<File> images, int channelId) throws Exception {
        List<byte[]> imagesRawData = new ArrayList();
        List<String> imgKeys = new ArrayList<>();
        convertMessageData(images, imagesRawData, imgKeys);
        String instructionsToSave = ImageUtil.modifyJsonWithImageKeys(instructions, keys, imgKeys);
        if(instructionsToSave == null) {
            throw new BadHttpRequest(new Exception("instructions don't match images"));
        }
        int userId = authenticationFacade.getUserId();
        MessageDtoFull messageDto = new MessageDtoFull(instructions, null, null, imgKeys, imagesRawData);
        try {
            return converter.create(messageDto, userId, channelId);
        } catch (IOException e) {
            throw new BadHttpRequest(new Exception("File saving problem"));
        }
    }

    private static void convertMessageData(List<File> images, List<byte[]> imagesRawData, List<String> imgKeys) throws BadHttpRequest {
        try {
            for (int i = 0; i < images.size(); i++) {
                BufferedImage image = ImageIO.read(images.get(i));
                if (image == null) {
                    throw new BadHttpRequest(new Exception("file is not an image"));
                }
                image = ImageUtil.resize(image, 300, 300);
                byte[] imageRawData = ImageUtil.convertImgToByteArray(image);
                String checkSum = ImageUtil.checkSum(imageRawData);
                imgKeys.add(checkSum);
                imagesRawData.add(imageRawData);
            }
        } catch (IOException e) {
            throw new BadHttpRequest(new Exception("file read problem"));
        }
    }


    public void delete(int id) throws NotFoundException {
        int userId = authenticationFacade.getUserId();
        converter.delete(id, userId);
    }

    public MessageDto get(int id) throws NotFoundException {
        return converter.get(id);
    }

    public List<MessageDto> getAll() {
        int userId = authenticationFacade.getUserId();
        return converter.getAll(userId);
    }

    public List<MessageDto> getAllByChannel(int channelId) {
        int userId = authenticationFacade.getUserId();
        return converter.getAllByChannel(channelId, userId);
    }

    public MessageDto getCurrentMessageByDevice(int deviceId) {
        int userId = authenticationFacade.getUserId();
        return converter.getCurrentMessageByDevice(deviceId, userId);
    }
}
