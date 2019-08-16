package top.lijunliang.permission.dao;

import org.springframework.stereotype.Repository;
import top.lijunliang.permission.entity.UserInfo;

import java.util.List;
import java.util.Map;

@Repository
public interface UserInfoDao
{
    void addU(UserInfo userInfo); //添加用户表

    void addUR(Map<String, Integer> map); //添加用户角色关联表

    List<UserInfo> selectAll();

    UserInfo findByUserName(UserInfo userInfo); //查找用户信息，角色信息，权限信息

    UserInfo findByUserId(UserInfo userInfo); //查找用户信息，角色信息

    void updateUserName(UserInfo userInfo);

    void deleteUser(UserInfo userInfo);

    void deleteRoleList(UserInfo userInfo);
}
