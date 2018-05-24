package com.minsk.pendulum.security;

import com.minsk.pendulum.model.Role;
import com.minsk.pendulum.model.User;
import com.minsk.pendulum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("provider")
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();
        final User user = userRepository.getByEmail(login);
        if (user != null && user.getPassword().equals(password)) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for(Role role: user.getRoles()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.name()));
            }
            AbstractAuthenticationToken abstractAuthenticationToken = new UsernamePasswordAuthenticationToken(login, password, grantedAuthorities);
            abstractAuthenticationToken.setDetails(user);
            return abstractAuthenticationToken;
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
