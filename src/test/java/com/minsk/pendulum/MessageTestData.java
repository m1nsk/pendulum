package com.minsk.pendulum;

import com.minsk.pendulum.model.Message;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;

import static com.minsk.pendulum.web.json.JsonUtil.writeIgnoreProps;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class MessageTestData {
    public static void assertMatch(Message actual, Message expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "date", "devices");
    }

    public static void assertMatch(Iterable<Message> actual, Message... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Message> actual, Iterable<Message> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("date", "devices").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(Message... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected)));
    }

    public static ResultMatcher contentJson(Message expected) {
        return content().json(writeIgnoreProps(expected));
    }
}
