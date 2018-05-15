package com.minsk.pendulum.components;

import com.minsk.pendulum.service.customService.MyUserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public int getUserId() {
        Authentication authentication = getAuthentication();
        return ((MyUserPrincipal)authentication.getPrincipal()).getUserId();
    }
}