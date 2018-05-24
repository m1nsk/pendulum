package com.minsk.pendulum.web.message;

import com.minsk.pendulum.DTO.message.MessageDto;
import com.minsk.pendulum.util.exception.InvalidDeviceInstructionsExeption;
import com.minsk.pendulum.util.exception.NotFoundException;
import com.minsk.pendulum.util.exception.WrongFileFormatExeption;
import javassist.tools.web.BadHttpRequest;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(value = MessageRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageRestController extends AbstractMessageRestController {
    public static final String REST_URL = "/rest/message";


    @PostMapping(value = "channel/{channelId}")
    public MessageDto create(MultipartHttpServletRequest request, @PathVariable("channelId") int channelId) throws Exception {
        //get form data fields
        final String instructions = request.getParameter("file");
        final String keys = request.getParameter("keys");

        if(!validateJson(instructions)){
            throw new InvalidDeviceInstructionsExeption("invalid instructions");
        }

        Iterator<String> iterator = request.getFileNames();
        List<File> images = new ArrayList<>();
        while (iterator.hasNext()) {
            File file = convertToImage(request.getFile(iterator.next()));
            if (file == null) {
                throw new WrongFileFormatExeption("invalid file format");
            }
            images.add(file);
        }
        return super.uploadMessage(instructions, keys, images, channelId);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable int id) throws NotFoundException {
        super.delete(id);
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MessageDto get(@PathVariable int id) throws NotFoundException {
        return super.get(id);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MessageDto> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping(value = "/channel/{channelId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MessageDto> getAllByChannel(@PathVariable("channelId") int channelId) {
        return super.getAllByChannel(channelId);
    }

    @Override
    @GetMapping(value = "/device/{deviceId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public MessageDto getCurrentMessageByDevice(@PathVariable("deviceId") int deviceId) {
        return super.getCurrentMessageByDevice(deviceId);
    }

    public static File convertToImage(MultipartFile file) {
        File convFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            convFile.createNewFile();
            fos.write(file.getBytes());
            return convFile;
        } catch (IOException e) {
            return null;
        }
    }

    private static boolean validateJson(String json) {
        return true;
    }

}
