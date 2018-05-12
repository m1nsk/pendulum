package com.minsk.pendulum.web.channel;

import com.minsk.pendulum.AuthorizedUser;
import com.minsk.pendulum.DTO.channel.ChannelDto;
import com.minsk.pendulum.DTO.DtoUtils;
import com.minsk.pendulum.model.Channel;
import com.minsk.pendulum.service.ChannelService;
import com.minsk.pendulum.util.exception.NotFoundException;
import com.minsk.pendulum.web.AbstractSecurityController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static com.minsk.pendulum.util.ValidationUtil.assureIdConsistent;
import static com.minsk.pendulum.util.ValidationUtil.checkNew;

public class AbstractChannelRestController extends AbstractSecurityController {

    @Autowired
    private ChannelService service;

    @Autowired
    private DtoUtils dtoUtils;

    public ChannelDto create(ChannelDto channelDto) {
        int userId =authenticationFacade.getUserId();
        Channel channel = dtoUtils.convertToEntity(channelDto);
        checkNew(channel);
        channel = service.create(channel, userId);
        return dtoUtils.convertToDto(channel);
    }

    public ChannelDto update(ChannelDto channelDto, int id) {
        int userId = authenticationFacade.getUserId();
        Channel channel = dtoUtils.convertToEntity(channelDto);
        assureIdConsistent(channel, id);
        channel = service.create(channel, userId);
        return dtoUtils.convertToDto(channel);
    }

    public void delete(int id) throws NotFoundException {
        int userId = authenticationFacade.getUserId();
        service.delete(id, userId);
    }

    public ChannelDto get(int id) throws NotFoundException {
        int userId = authenticationFacade.getUserId();
        return dtoUtils.convertToDto(service.get(id, userId));
    }

    public List<ChannelDto> getAll() {
        int userId = authenticationFacade.getUserId();
        List<Channel> channels = service.getAll(userId);
        return channels.stream().map(channel -> dtoUtils.convertToDto(channel))
                .collect(Collectors.toList());
    }
}
