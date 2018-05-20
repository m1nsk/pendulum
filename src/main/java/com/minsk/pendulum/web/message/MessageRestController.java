package com.minsk.pendulum.web.message;

import com.minsk.pendulum.DTO.message.MessageDto;
import com.minsk.pendulum.util.exception.NotFoundException;
import org.springframework.http.MediaType;
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

//    @Override
//    @PostMapping(value = "channel/{channelId}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public MessageDto create(@RequestBody MessageDto messageDto, @PathVariable("channelId") int channelId) {
//        return super.create(messageDto, channelId);
//    }

    @PostMapping(value = "/channel")
    public String handlePost(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException {
        //get form data fields
        final String instructions = request.getParameter("file");
        final String keys = request.getParameter("keys");

        if(!validateJson(instructions)){
            return "404";
        }

        Iterator<String> iterator = request.getFileNames();
        File multipartFile = null;
        List<BufferedImage> images = new ArrayList();
        List<Integer> imgKeys = new ArrayList<>();
        while (iterator.hasNext()) {
            multipartFile = convertToImage(request.getFile(iterator.next()));
            BufferedImage image = ImageIO.read(multipartFile);
            if (image == null) {
                return "404";
            }
            image = resize(image, 300, 300);
            if (image == null) {
                return "404";
            }
            System.out.println(image.hashCode());
            imgKeys.add(image.hashCode());
            images.add(image);
        }
        String instructionsToSave = modifyJsonWithImageKeys(instructions, keys, imgKeys);
        if(instructionsToSave == null) {
            return "404";
        }

        return "101";
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

    public static File convertToImage(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    private static void storeImage(BufferedImage image) {
    }

    private static boolean validateJson(String json) {
        return true;
    }

    private static String modifyJsonWithImageKeys(String instructions, String keys, List<Integer> imgKeys) {
        return instructions;
    }

    private static boolean saveInstructionsAndImages(String instructions, List<BufferedImage>) {
        return true;
    }
}
