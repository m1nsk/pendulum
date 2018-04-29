package com.minsk.pendulum.service;

import com.minsk.pendulum.model.Device;
import com.minsk.pendulum.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.minsk.pendulum.DeviceTestData.*;
import static com.minsk.pendulum.EntityTestData.*;

public abstract class DeviceAbstractServiceTest extends BaseServiceTest {

    @Autowired
    protected DeviceService service;

    @Test
    public void delete() throws Exception {
        service.delete(DEVICE1_ID, USER_ID);
        assertMatch(service.getAll(USER_ID), DEVICE2);
    }

    @Test
    public void deleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(DEVICE1_ID, 1);
    }

    @Test
    public void create() throws Exception {
        Device created = getDeviceCreated();
        service.create(created, USER_ID);
        assertMatch(service.getAll(USER_ID), created, DEVICE2, DEVICE1);
    }

    @Test
    public void get() throws Exception {
        Device actual = service.get(ADMIN_DEVICE_ID, ADMIN_ID);
        assertMatch(actual, ADMIN_DEVICE1);
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(DEVICE1_ID, ADMIN_ID);
    }

    @Test
    public void update() throws Exception {
        Device updated = getDeviceUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(DEVICE1_ID, USER_ID), updated);
    }

    @Test
    public void updateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + DEVICE1_ID);
        service.update(DEVICE1, ADMIN_ID);
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(USER_ID), DEVICES);
    }

}