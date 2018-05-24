package com.minsk.pendulum.web;

import com.minsk.pendulum.security.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractSecurityController {
    @Autowired
    protected IAuthenticationFacade authenticationFacade;
}
