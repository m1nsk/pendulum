package com.minsk.pendulum.web.channel;

import com.minsk.pendulum.model.Channel;
import com.minsk.pendulum.util.exception.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = ChannelRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ChannelRestController extends AbstractChannelRestController {
    public static final String REST_URL = "/rest/channel";

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Channel create(@RequestBody Channel channel) {
        return super.create(channel);
    }

    @Override
    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Channel update(@RequestBody Channel channel, @PathVariable int id) {
        return super.update(channel, id);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable int id) throws NotFoundException {
        super.delete(id);
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Channel get(@PathVariable int id) throws NotFoundException {
        return super.get(id);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Channel> getAll() {
        return super.getAll();
    }
}
