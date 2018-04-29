package com.minsk.pendulum;

import com.minsk.pendulum.model.Role;
import com.minsk.pendulum.model.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static com.minsk.pendulum.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTestData {
    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "roles", "channels", "devices");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "roles", "channels", "devices").isEqualTo(expected);
    }
}
