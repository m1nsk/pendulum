package com.minsk.pendulum;

import com.minsk.pendulum.model.Channel;
import com.minsk.pendulum.model.Message;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;
import java.util.List;

import static com.minsk.pendulum.UserTestData.*;
import static com.minsk.pendulum.model.AbstractBaseEntity.START_SEQ;
import static com.minsk.pendulum.web.json.JsonUtil.writeIgnoreProps;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class ChannelTestData {
    public static final int CHANNEL1_ID = START_SEQ + 5;
    public static final int ADMIN_CHANNEL_ID = START_SEQ + 7;

    public static final Channel CHANNEL1 = new Channel(CHANNEL1_ID ,new Message(), USER, null, null);
    public static final Channel CHANNEL2 = new Channel(CHANNEL1_ID + 1 ,new Message(), USER, null, null);
    public static final Channel ADMIN_CHANNEL1 = new Channel(ADMIN_CHANNEL_ID ,new Message(), ADMIN, null, null);

    public static final List<Channel> CHANNELS = Arrays.asList(CHANNEL2, CHANNEL1);

    public static Channel getCreated() {
        return new Channel(null, new Message(), USER);
    }

    public static Channel getUpdated() {
        return new Channel(CHANNEL1.getId(), CHANNEL1.getMessage(), CHANNEL1.getUser(), null, DEVICE1);
    }

    public static void assertMatch(Channel actual, Channel expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user");
    }

    public static void assertMatch(Iterable<Channel> actual, Channel... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Channel> actual, Iterable<Channel> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(Channel... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected)));
    }

    public static ResultMatcher contentJson(Channel expected) {
        return content().json(writeIgnoreProps(expected));
    }
}
