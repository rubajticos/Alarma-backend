package com.appstudiomr.apps.alarma.config.firebase;

import com.appstudiomr.apps.alarma.exception.FirebaseTokenInvalidException;
import com.appstudiomr.apps.alarma.service.FirebaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FirebaseFilter extends OncePerRequestFilter {

    private static String HEADER_NAME = "X-Authorization-Firebase";

    private FirebaseService firebaseService;

    public FirebaseFilter(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String xAuth = request.getHeader(HEADER_NAME);
        if (StringUtils.isBlank(xAuth)) {
            response.getWriter().println("Authentication not provided!");
        } else {
            try {
                FirebaseTokenHolder holder = firebaseService.parseToken(xAuth);

                String userUid = holder.getUid();

                Authentication auth = new FirebaseAuthenticationToken(userUid, holder);
                SecurityContextHolder.getContext().setAuthentication(auth);

                filterChain.doFilter(request, response);
            } catch (FirebaseTokenInvalidException e) {
                response.getWriter().println("Token is invalid");
            }
        }
    }

}
