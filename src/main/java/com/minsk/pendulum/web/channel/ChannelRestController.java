package com.minsk.pendulum.web.channel;

import com.minsk.pendulum.DTO.channel.ChannelDto;
import com.minsk.pendulum.util.exception.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(value = ChannelRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ChannelRestController extends AbstractChannelRestController {
    public static final String REST_URL = "/rest/channel";

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ChannelDto create(@RequestBody ChannelDto channelDto) {
        return super.create(channelDto);
    }

    @Override
    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ChannelDto update(@RequestBody ChannelDto channelDto, @PathVariable int id) {
        return super.update(channelDto, id);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable int id) throws NotFoundException {
        super.delete(id);
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ChannelDto get(@PathVariable int id) throws NotFoundException {
        return super.get(id);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ChannelDto> getAll() {
        return super.getAll();
    }
}
