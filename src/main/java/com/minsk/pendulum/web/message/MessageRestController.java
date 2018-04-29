package com.minsk.pendulum.web.message;

import com.minsk.pendulum.model.Message;
import com.minsk.pendulum.util.exception.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = MessageRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageRestController extends AbstractMessageRestController {
    public static final String REST_URL = "/rest/message";

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Message create(@RequestBody Message message) {
        return super.create(message);
    }

    @Override
    @DeleteMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable int id) throws NotFoundException {
        super.delete(id);
    }

    @Override
    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Message get(@PathVariable int id) throws NotFoundException {
        return super.get(id);
    }

    @Override
    @GetMapping(value = "/all",consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Message> getAll(int userId) {
        return super.getAll(userId);
    }
}
