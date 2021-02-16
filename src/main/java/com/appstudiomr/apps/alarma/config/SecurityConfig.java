package com.appstudiomr.apps.alarma.config;

import com.appstudiomr.apps.alarma.config.firebase.FirebaseAuthenticationProvider;
import com.appstudiomr.apps.alarma.config.firebase.FirebaseFilter;
import com.appstudiomr.apps.alarma.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Configuration
    protected static class AuthenticationSecurity extends GlobalAuthenticationConfigurerAdapter {

        @Autowired
        private FirebaseAuthenticationProvider firebaseProvider;

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(firebaseProvider);
        }
    }

    @Configuration
    protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {

        @Autowired
        private FirebaseService firebaseService;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.addFilterBefore(tokenAuthorizationFilter(), BasicAuthenticationFilter.class).authorizeRequests()
                    .anyRequest()
                    .authenticated()
                    .and().csrf().disable();
        }

        private FirebaseFilter tokenAuthorizationFilter() {
            return new FirebaseFilter(firebaseService);
        }

    }
}


