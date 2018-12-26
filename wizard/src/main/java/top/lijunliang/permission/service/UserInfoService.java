package top.lijunliang.permission.service;

import org.apache.catalina.User;
import org.omg.CORBA.PUBLIC_MEMBER;
import top.lijunliang.permission.entity.UserInfo;
import top.lijunliang.permission.utils.StatusCode;

import java.util.List;

public interface UserInfoService
{
    public StatusCode addUserInfo(UserInfo userInfo);

    public List<UserInfo> listUserInfo();

    public UserInfo findByUserName(UserInfo userInfo);

    public StatusCode deleteUserInfo(UserInfo userInfo);

    public StatusCode updateUserInfo(UserInfo userInfo);

    public UserInfo findUserRoleList(UserInfo userInfo);
}
