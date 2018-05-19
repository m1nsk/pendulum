package com.minsk.pendulum.web.message;

import com.minsk.pendulum.DTO.message.MessageDto;
import com.minsk.pendulum.util.exception.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
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
        final String field= request.getParameter("file");
        //and so on......

        //Now get the files.
        Iterator<String> iterator = request.getFileNames();
        File multipartFile = null;
        Image image = null;
        String mimetype = null;
        while (iterator.hasNext()) {
            multipartFile = convert(request.getFile(iterator.next()));
            image = ImageIO.read(multipartFile);
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

    public File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
