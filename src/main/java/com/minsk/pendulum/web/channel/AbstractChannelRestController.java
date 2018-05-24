package com.minsk.pendulum.web.channel;

import com.minsk.pendulum.DTO.channel.ChannelDto;
import com.minsk.pendulum.converter.ChannelConverterImpl;
import com.minsk.pendulum.util.exception.NotFoundException;
import com.minsk.pendulum.web.AbstractSecurityController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.minsk.pendulum.util.ValidationUtil.assureIdConsistent;
import static com.minsk.pendulum.util.ValidationUtil.checkNew;

public class AbstractChannelRestController extends AbstractSecurityController {

    @Autowired
    private ChannelConverterImpl converter;

    public ChannelDto create(ChannelDto channelDto) {
        checkNew(channelDto);
        int userId =authenticationFacade.getUserId();
        return converter.create(channelDto, userId);
    }

    public ChannelDto update(ChannelDto channelDto, int id) {
        assureIdConsistent(channelDto, id);
        int userId = authenticationFacade.getUserId();
        return converter.update(channelDto, userId);
    }

    public void addDevice(int channelId, int deviceId) {
        int userId = authenticationFacade.getUserId();
        converter.addDevice(channelId, deviceId);
    }

    public void delete(int id) throws NotFoundException {
        int userId = authenticationFacade.getUserId();
        converter.delete(id, userId);
    }

    public ChannelDto get(int id) throws NotFoundException {
        int userId = authenticationFacade.getUserId();
        return converter.get(id, userId);
    }

    public List<ChannelDto> getAll() {
        int userId = authenticationFacade.getUserId();
        return converter.getAll(userId);
    }

    public List<ChannelDto> getAllByDevice(int deviceId) {
        int userId = authenticationFacade.getUserId();
        return converter.getAllByDevice(deviceId, userId);
    }
}
