package com.appstudiomr.apps.alarma.config.firebase;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class FirebaseAuthenticationProvider implements AuthenticationProvider {

    public boolean supports(Class<?> authentication) {
        return (FirebaseAuthenticationToken.class.isAssignableFrom(authentication));
    }

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!supports(authentication.getClass())) {
            return null;
        }

        return authentication;
    }

}
