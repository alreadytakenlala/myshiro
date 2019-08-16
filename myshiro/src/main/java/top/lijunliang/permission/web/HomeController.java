package top.lijunliang.permission.web;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.lijunliang.permission.ApplicationServer;
import top.lijunliang.permission.entity.UserInfo;
import top.lijunliang.permission.utils.StatusCode;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController
{
    protected static final Logger logger = LoggerFactory.getLogger(ApplicationServer.class);

    @RequestMapping("/index")
    public StatusCode index(HttpSession session)
    {
        printInformation(session);
        return new StatusCode(2000, "登录成功", session.getId());
    }

    @RequestMapping("/login")
    public StatusCode login(HttpServletRequest request, HttpSession session)
    {
        printInformation(session);
        String exception = (String) request.getAttribute("shiroLoginFailure");

        if (exception != null)
        {
            if (UnknownAccountException.class.getName().equals(exception))
                return new StatusCode(2001, "账号不存在");
            else if (IncorrectCredentialsException.class.getName().equals(exception))
                return new StatusCode(2002, "密码不正确");
            else if ("kaptchaValidateFailed".equals(exception))
                return new StatusCode(2003, "验证码错误");
            else
            {
                return new StatusCode(2004, exception);
            }
        }

        return new StatusCode(2000,"尚未登录");
    }

    @RequestMapping("/403")
    public StatusCode unauthorizedRole()
    {
        return new StatusCode(403, "没有权限");
    }

    private void printInformation(HttpSession session)
    {
        if (session.getAttribute(DefaultSubjectContext.AUTHENTICATED_SESSION_KEY).equals(true))
        logger.info("登录用户为：" + session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
    }
}
