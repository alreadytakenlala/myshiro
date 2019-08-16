package top.lijunliang.permission.filter;

import com.google.gson.Gson;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import top.lijunliang.permission.ApplicationServer;
import top.lijunliang.permission.entity.UserInfo;
import top.lijunliang.permission.service.UserInfoService;
import top.lijunliang.permission.utils.ServerResponse;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 重写默认的FormAuthenticationFilter，
 * 在onAccessDeny（）方法中：
 * 如果请求的是loginUrl，则调用AuthenticatingFilter.executeLogin()
 * 如果不是，则返回json，提示“未登录，无法访问该地址”
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter
{
    protected static final Logger logger = LoggerFactory.getLogger(ApplicationServer.class);

    @Autowired
    private UserInfoService userInfoService;

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception
    {
        logger.info("FormAuthenticationFilter.onAccessDenied");
        if (this.isLoginRequest(request, response))
        {
            logger.info("请求为登录页面" + ", url:" + this.getLoginUrl());
            if (this.isLoginSubmission(request, response))
            {
                logger.info("是登录提交");
                return this.executeLogin(request, response);
            }
            else
            {
                logger.info("不是登录提交");
                generateJson(response,"尚未登录");
                return true;
            }
        } else
        {
            logger.info("请求非登录界面" + ", url:" + this.getLoginUrl());
            generateJson(response, "未登录，无法访问该地址");

            return false;
        }
    }


    /**
     * 由于AuthenticatingFilter.executeLogin()会调用onLoginSuccess()和onLoginFailure()方法
     * 所以我们重写两个方法，前者返回登录成功的json，并把当前用户放入session；后者返回登录失败的json
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception
    {
        logger.info("onLoginSuccess");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        HttpSession session = ((HttpServletRequest)request).getSession();
        UserInfo user = userInfoService.findByUserName(new UserInfo(token.getPrincipal().toString()));
        logger.info("在session中存入" + user.getName());
        session.setAttribute(user.getName(), user);
        ServerResponse serverResponse = ServerResponse.createBySuccessMessage("登录成功");
        Gson gson = new Gson();
        String s = gson.toJson(serverResponse);
        out.println(s);
        out.flush();
        out.close();

        return true;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        logger.info("onLoginFailure");
        generateJson(response,"账号或密码错误");
        return false;
    }

    public void generateJson(ServletResponse response, String errorMessage)
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        ServerResponse serverResponse = ServerResponse.createByErrorMessage(errorMessage);
        Gson gson = new Gson();
        String s = gson.toJson(serverResponse);
        out.println(s);
        out.flush();
        out.close();
    }

}
