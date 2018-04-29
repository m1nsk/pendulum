package com.minsk.pendulum;

import com.minsk.pendulum.model.Device;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;

import static com.minsk.pendulum.web.json.JsonUtil.writeIgnoreProps;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class DeviceTestData {
    public static void assertMatch(Device actual, Device expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Device> actual, Device... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Device> actual, Iterable<Device> expected) {
        assertThat(actual).usingDefaultComparator().isEqualTo(expected);
    }

    public static ResultMatcher contentJson(Device... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected)));
    }

    public static ResultMatcher contentJson(Device expected) {
        return content().json(writeIgnoreProps(expected));
    }
}
