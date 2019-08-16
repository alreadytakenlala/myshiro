package top.lijunliang.permission.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.lijunliang.permission.dao.ArticleDao;
import top.lijunliang.permission.dao.RoleDao;
import top.lijunliang.permission.dao.UserInfoDao;
import top.lijunliang.permission.entity.Article;
import top.lijunliang.permission.entity.SysPermission;
import top.lijunliang.permission.entity.SysRole;
import top.lijunliang.permission.entity.UserInfo;

import javax.management.relation.Role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ArticleServiceImplTest
{
    @Autowired
    private UserInfoDao userInfoDao;

    @Test
    public void test()
    {
        UserInfo userInfo = userInfoDao.findByUserName(new UserInfo("wizard"));
        System.out.println(userInfo.getName());
        System.out.println(userInfo.getPassword());
        System.out.println(userInfo.getSalt());
        for (SysRole sysRole : userInfo.getRoleList())
        {
            System.out.println(sysRole.getId());
            System.out.println(sysRole.getName());
            for (SysPermission sysPermission : sysRole.getPermissionList())
            {
                System.out.println(sysPermission.getId());
                System.out.println(sysPermission.getName());
                System.out.println(sysPermission.getPermission());
            }
        }
    }
}
