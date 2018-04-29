package com.minsk.pendulum.web.channel;

import com.minsk.pendulum.AuthorizedUser;
import com.minsk.pendulum.model.Channel;
import com.minsk.pendulum.service.ChannelService;
import com.minsk.pendulum.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.minsk.pendulum.util.ValidationUtil.assureIdConsistent;
import static com.minsk.pendulum.util.ValidationUtil.checkNew;

public class AbstractChannelRestController {

    @Autowired
    private ChannelService service;

    public Channel create(Channel channel) {
        int userId =AuthorizedUser.id();
        checkNew(channel);
        return service.create(channel, userId);
    }

    public Channel update(Channel channel, int id) {
        int userId = AuthorizedUser.id();
        assureIdConsistent(channel, id);
        return service.create(channel, userId);
    }

    public void delete(int id) throws NotFoundException {
        int userId = AuthorizedUser.id();
        service.delete(id, userId);
    }

    public Channel get(int id) throws NotFoundException {
        int userId = AuthorizedUser.id();
        return service.get(id, userId);
    }

    public List<Channel> getAll(int userId) {
        return service.getAll(userId);
    }
}
