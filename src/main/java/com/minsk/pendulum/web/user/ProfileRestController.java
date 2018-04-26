package com.minsk.pendulum.web.user;

import org.springframework.stereotype.Controller;
import com.minsk.pendulum.AuthorizedUser;
import com.minsk.pendulum.model.User;

@Controller
public class ProfileRestController extends AbstractUserController {

    public User get() {
        return super.get(AuthorizedUser.id());
    }

    public void delete() {
        super.delete(AuthorizedUser.id());
    }

    public void update(User user) {
        super.update(user, AuthorizedUser.id());
    }
}