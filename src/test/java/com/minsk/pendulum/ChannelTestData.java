package com.minsk.pendulum;

import com.minsk.pendulum.model.Channel;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;

import static com.minsk.pendulum.web.json.JsonUtil.writeIgnoreProps;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class ChannelTestData {

    public static void assertMatch(Channel actual, Channel expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "devices", "messages", "message");
    }

    public static void assertMatch(Iterable<Channel> actual, Channel... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Channel> actual, Iterable<Channel> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("devices", "messages", "message").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(Channel... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected)));
    }

    public static ResultMatcher contentJson(Channel expected) {
        return content().json(writeIgnoreProps(expected));
    }
}
