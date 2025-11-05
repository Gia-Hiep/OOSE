package com.example.interceptordemo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(RequestInterceptor.class);
    private final ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 1) start timer
        startTime.set(System.currentTimeMillis());

        // 2) very simple auth check via session
        String uri = request.getRequestURI();
        Object user = request.getSession().getAttribute("user");

        // Allow public endpoints (configured in WebConfig too),
        // but we keep a defensive check here in case config changes.
        boolean isPublic = uri.startsWith("/login")
                        || uri.startsWith("/403")
                        || uri.startsWith("/css/")
                        || uri.startsWith("/js/")
                        || uri.startsWith("/images/")
                        || "/".equals(uri);

        if (!isPublic && user == null) {
            // not logged in -> redirect to /login
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        Long started = startTime.get();
        if (started != null) {
            long duration = System.currentTimeMillis() - started;
            log.info("[INTERCEPTOR] {} {} -> {} ({} ms)",
                    request.getMethod(),
                    request.getRequestURI(),
                    response.getStatus(),
                    duration);
            startTime.remove();
        }
        if (ex != null) {
            log.error("Request completed with exception", ex);
        }
    }
}
