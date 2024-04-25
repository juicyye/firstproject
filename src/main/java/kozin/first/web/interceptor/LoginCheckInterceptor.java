package kozin.first.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kozin.first.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        String requestURI = request.getRequestURI();
        if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) ==null) {
            response.sendRedirect("/login?redirect="+requestURI);
            log.info("미인증 사용자");
            return false;
        }

        return true;
    }
}
