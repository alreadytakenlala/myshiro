package top.lijunliang.permission.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.lijunliang.permission.ApplicationServer;
import top.lijunliang.permission.entity.SysPermission;
import top.lijunliang.permission.entity.SysRole;
import top.lijunliang.permission.entity.UserInfo;
import top.lijunliang.permission.service.UserInfoService;

import javax.servlet.http.HttpServletResponse;

public class MyShiroRealm extends AuthorizingRealm
{
    @Autowired
    private UserInfoService userInfoService;

    protected static final Logger logger = LoggerFactory.getLogger(ApplicationServer.class);

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection)
    {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo(); //授权信息
        UserInfo userInfo = (UserInfo) principalCollection.getPrimaryPrincipal();

        for (SysRole role : userInfoService.findByUserName(userInfo).getRoleList())
        {
            authorizationInfo.addRole(role.getName());
            for (SysPermission p : role.getPermissionList())
            {
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        logger.info(userInfo.getName() + ":授权");
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException
    {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //获取用户的输入账号
        String username = token.getUsername();
        //通过username从数据库中查找User对象
        UserInfo userInfo = userInfoService.findByUserName(new UserInfo(username));
        if (userInfo == null) return null;
        System.out.println(userInfo.getSalt());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, userInfo.getPassword(),
                ByteSource.Util.bytes(userInfo.getSalt()),
                getName());
        logger.info(userInfo.getName() + ":认证");
        return authenticationInfo;
    }
}
