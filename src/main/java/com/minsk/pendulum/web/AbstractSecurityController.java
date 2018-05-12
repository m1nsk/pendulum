package com.minsk.pendulum.web;

import com.minsk.pendulum.components.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractSecurityController {
    @Autowired
    protected IAuthenticationFacade authenticationFacade;
}
