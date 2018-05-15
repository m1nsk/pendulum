package com.minsk.pendulum.web.message;

import com.minsk.pendulum.DTO.message.MessageDto;
import com.minsk.pendulum.util.exception.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(value = MessageRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageRestController extends AbstractMessageRestController {
    public static final String REST_URL = "/rest/message";

    @Override
    @PostMapping(value = "channel/{channelId}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public MessageDto create(@RequestBody MessageDto messageDto, @PathVariable("channelId") int channelId) {
        return super.create(messageDto, channelId);
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
}
