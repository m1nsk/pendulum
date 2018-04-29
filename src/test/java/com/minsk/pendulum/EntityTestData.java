package com.minsk.pendulum;

import com.minsk.pendulum.model.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.minsk.pendulum.model.AbstractBaseEntity.START_SEQ;

public class EntityTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;


    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password",true, new Date(), Collections.singleton(Role.ROLE_USER), null, null);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", true, new Date(), Collections.singleton(Role.ROLE_ADMIN), null, null);

    public static final int DEVICE1_ID = START_SEQ + 2;
    public static final int ADMIN_DEVICE_ID = START_SEQ + 4;

    public static final Device DEVICE1 = new Device(DEVICE1_ID, DEVICE1_ID, USER);
    public static final Device DEVICE2 = new Device(DEVICE1_ID + 1, DEVICE1_ID + 1, USER);
    public static final Device ADMIN_DEVICE1 = new Device(ADMIN_DEVICE_ID, ADMIN_DEVICE_ID, ADMIN);

    public static final List<Device> DEVICES = Arrays.asList(DEVICE2, DEVICE1);

    public static final int CHANNEL1_ID = START_SEQ + 5;
    public static final int ADMIN_CHANNEL_ID = CHANNEL1_ID + 2;

    public static final Channel CHANNEL1 = new Channel(CHANNEL1_ID , null, USER, null, null);
    public static final Channel CHANNEL2 = new Channel(CHANNEL1_ID + 1 , null, USER, null, null);
    public static final Channel ADMIN_CHANNEL1 = new Channel(ADMIN_CHANNEL_ID , null, ADMIN, null, null);

    public static final List<Channel> CHANNELS = Arrays.asList(CHANNEL2, CHANNEL1);

    public static final int MESSAGE1_ID = START_SEQ;
    public static final int ADMIN1_MESSAGE_ID = START_SEQ + 3;

    public static final Message MESSAGE1 = new Message(MESSAGE1_ID, "{\"key\":\"empty1\"}", CHANNEL1, USER);
    public static final Message MESSAGE2 = new Message(MESSAGE1_ID + 1, "{\"key\":\"empty2\"}", CHANNEL2, USER);
    public static final Message MESSAGE3 = new Message(MESSAGE1_ID + 2, "{\"key\":\"empty3\"}", ADMIN_CHANNEL1, USER);
    public static final Message ADMIN_MESSAGE1 = new Message(ADMIN1_MESSAGE_ID, "{\"key\":\"empty4\"}", CHANNEL1, ADMIN);
    public static final Message ADMIN_MESSAGE2 = new Message(ADMIN1_MESSAGE_ID + 1, "{\"key\":\"empty5\"}", CHANNEL1, ADMIN);

    public static final List<Message> MESSAGES = Arrays.asList(MESSAGE3, MESSAGE2, MESSAGE1);

    static {
        CHANNEL1.setMessage(MESSAGE1);
        CHANNEL2.setMessage(MESSAGE2);
        ADMIN_CHANNEL1.setMessage(MESSAGE3);

        CHANNEL1.setMessages(Arrays.asList(MESSAGE1, ADMIN_MESSAGE1, ADMIN_MESSAGE2));
        CHANNEL2.setMessages(Arrays.asList(MESSAGE2));
        ADMIN_CHANNEL1.setMessages(Arrays.asList(MESSAGE3));
    }

    public static Device getDeviceCreated() {
        return new Device(null, 100100, USER);
    }

    public static Device getDeviceUpdated() {
        return new Device(DEVICE1_ID, DEVICE1.getSerial() + 1,DEVICE1.getUser());
    }

    public static Channel getChannelCreated() {
        return new Channel(USER);
    }

    public static Channel getChannelUpdated() {
        return new Channel(CHANNEL1.getId(), CHANNEL1.getMessage(), CHANNEL1.getUser(), null, null);
    }

    public static Message getMessageCreated() {
        return new Message("{\"key\":\"empty1\"}", CHANNEL1, USER);
    }

}
