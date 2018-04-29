package com.minsk.pendulum.service;

import com.minsk.pendulum.model.Channel;
import com.minsk.pendulum.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.minsk.pendulum.ChannelTestData.*;
import static com.minsk.pendulum.EntityTestData.*;

public abstract class ChannelAbstractServiceTest extends BaseServiceTest {

    @Autowired
    protected ChannelService service;

    @Test
    public void delete() throws Exception {
        service.delete(CHANNEL1_ID, USER_ID);
        assertMatch(service.getAll(USER_ID), CHANNEL2);
    }

    @Test
    public void deleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(CHANNEL1_ID, 1);
    }

    @Test
    public void create() throws Exception {
        Channel created = getChannelCreated();
        service.create(created, USER_ID);
        assertMatch(service.getAll(USER_ID), created, CHANNEL2, CHANNEL1);
    }

    @Test
    public void get() throws Exception {
        Channel actual = service.get(ADMIN_CHANNEL_ID, ADMIN_ID);
        assertMatch(actual, ADMIN_CHANNEL1);
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(CHANNEL1_ID, ADMIN_ID);
    }

    @Test
    public void update() throws Exception {
        Channel updated = getChannelUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(CHANNEL1_ID, USER_ID), updated);
    }

    @Test
    public void updateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + CHANNEL1_ID);
        service.update(CHANNEL1, ADMIN_ID);
    }

    @Test
    public void getAll() throws Exception {
        List<Channel> channels = service.getAll(USER_ID);
        assertMatch(service.getAll(USER_ID), CHANNELS);
    }

}