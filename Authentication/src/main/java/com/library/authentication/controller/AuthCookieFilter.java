package com.library.authentication.controller;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.library.authentication.model.AppUserDetail;
import com.library.authentication.repository.SessionRepository;
import com.library.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AuthCookieFilter extends GenericFilterBean {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionRepository sessionRepository;
    public final static String COOKIE_NAME = "authentication";


    private final Cache<String, AppUserDetail> userDetailsCache;

    public AuthCookieFilter() {
        this.userDetailsCache = Caffeine.newBuilder().expireAfterAccess(1, TimeUnit.MINUTES)
                .maximumSize(1_000).build();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String sessionId = extractAuthenticationCookie(httpServletRequest);

        if (sessionId != null) {
            final String sId = sessionId;
            AppUserDetail userDetails = this.userDetailsCache.get(sessionId, key -> {
//                var record = userRepository.FindUserBySessionId(sId);
            var sessionInfo = sessionRepository.findSessionById(sId);
            if(!sessionInfo.isEmpty()) {
                var record = userRepository.findById(sessionInfo.get(0).getUser().getId());
                if (record.isPresent()) {
                    return new AppUserDetail(record.get());
                }
                return null;
            }
                return null;
            });

            if (userDetails != null && userDetails.isEnabled()) {
                SecurityContextHolder.getContext().setAuthentication(new UserAuthentication(userDetails));
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    public static String extractAuthenticationCookie(HttpServletRequest httpServletRequest) {
        String sessionId = null;
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(AuthCookieFilter.COOKIE_NAME)) {
                    sessionId = cookie.getValue();
                    break;
                }
            }
        }
        return sessionId;
    }
}