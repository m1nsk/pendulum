package com.minsk.pendulum;

import com.minsk.pendulum.model.Role;
import com.minsk.pendulum.model.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static com.minsk.pendulum.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password",true, new Date(), Collections.singleton(Role.ROLE_USER), null, null);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", true, new Date(), Collections.singleton(Role.ROLE_ADMIN), null, null);


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
