package com.minsk.pendulum;

import org.springframework.test.web.servlet.ResultMatcher;
import com.minsk.pendulum.model.Device;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static com.minsk.pendulum.model.AbstractBaseEntity.START_SEQ;
import static com.minsk.pendulum.web.json.JsonUtil.writeIgnoreProps;
import static com.minsk.pendulum.UserTestData.*;

public class DeviceTestData {
    public static final int DEVICE1_ID = START_SEQ + 2;
    public static final int ADMIN_DEVICE_ID = START_SEQ + 4;

    public static final Device DEVICE1 = new Device(DEVICE1_ID, DEVICE1_ID, USER);
    public static final Device DEVICE2 = new Device(DEVICE1_ID + 1, DEVICE1_ID + 1, USER);
    public static final Device ADMIN_DEVICE1 = new Device(ADMIN_DEVICE_ID, ADMIN_DEVICE_ID, ADMIN);

    public static final List<Device> DEVICES = Arrays.asList(DEVICE2, DEVICE1);

    public static Device getCreated() {
        return new Device(null, 100100, USER);
    }

    public static Device getUpdated() {
        return new Device(DEVICE1_ID, DEVICE1.getSerial() + 1,DEVICE1.getUser());
    }

    public static void assertMatch(Device actual, Device expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user");
    }

    public static void assertMatch(Iterable<Device> actual, Device... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Device> actual, Iterable<Device> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(Device... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected)));
    }

    public static ResultMatcher contentJson(Device expected) {
        return content().json(writeIgnoreProps(expected));
    }
}
