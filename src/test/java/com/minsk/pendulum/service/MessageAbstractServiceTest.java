package com.minsk.pendulum.service;

import com.minsk.pendulum.model.Message;
import com.minsk.pendulum.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.minsk.pendulum.MessageTestData.assertMatch;
import static com.minsk.pendulum.EntityTestData.*;

public abstract class MessageAbstractServiceTest extends BaseServiceTest {

    @Autowired
    protected MessageService service;

    @Test
    public void delete() throws Exception {
        service.delete(MESSAGE1_ID, USER_ID);
        assertMatch(service.getAll(USER_ID), MESSAGE3, MESSAGE2);
    }

    @Test
    public void deleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(MESSAGE1_ID, 1);
    }

    @Test
    public void create() throws Exception {
        Message created = getMessageCreated();
        service.create(created, USER_ID, CHANNEL1_ID);
        assertMatch(service.getAll(USER_ID), created, MESSAGE3, MESSAGE2, MESSAGE1);
    }

    @Test
    public void get() throws Exception {
        Message actual = service.get(ADMIN1_MESSAGE_ID);
        assertMatch(actual, ADMIN_MESSAGE1);
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(MESSAGE1_ID + 11);
    }

    @Test
    public void getAll() throws Exception {
        List<Message> channels = service.getAll(USER_ID);
        assertMatch(service.getAll(USER_ID), MESSAGES);
    }

}