package top.lijunliang.permission.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lijunliang.permission.dao.MenuDao;
import top.lijunliang.permission.dao.UserInfoDao;
import top.lijunliang.permission.entity.Menu;
import top.lijunliang.permission.entity.SysRole;
import top.lijunliang.permission.entity.UserInfo;
import top.lijunliang.permission.service.UserInfoService;
import top.lijunliang.permission.utils.Md5Util;
import top.lijunliang.permission.utils.CodeUtil;
import top.lijunliang.permission.utils.StatusCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService
{

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private MenuDao menuDao;

    @Override
    public StatusCode addUserInfo(UserInfo userInfo)
    {
        for (UserInfo user : userInfoDao.selectAll())
        {
            if (user.getName().equals(userInfo.getName()))
            {
                return new StatusCode(2005, "用户名重复");
            }
        }
        menuDao.add(new Menu(CodeUtil.createRandomNumber(), "添加", "admin:add"));
        String salt = CodeUtil.createRandomNumber();
        userInfo.setSalt(salt);
        userInfo.setPassword(Md5Util.encryption(userInfo.getPassword(), "MD5", salt, 2)); //MD5加密
        userInfoDao.addU(userInfo);

        for (SysRole sysRole : userInfo.getRoleList())
        {
            Map<String, Integer> map = new HashMap<>();
            Integer uid = userInfo.getId();
            Integer rid = sysRole.getId();
            map.put("uid", uid);
            map.put("rid", rid);
            userInfoDao.addUR(map);
        }
        return new StatusCode(2000, "添加管理员成功");
    }

    @Override
    public List<UserInfo> listUserInfo()
    {
        return userInfoDao.selectAll();
    }

    @Override
    public UserInfo findByUserName(UserInfo userInfo)
    {
        return userInfoDao.findByUserName(userInfo);
    }

    @Override
    public StatusCode deleteUserInfo(UserInfo userInfo)
    {
        userInfoDao.deleteUser(userInfo);
        return new StatusCode(2000, "管理员删除成功");
    }

    @Override
    public StatusCode updateUserInfo(UserInfo userInfo)
    {
        userInfoDao.deleteRoleList(userInfo);
        userInfoDao.updateUserName(userInfo);
        for (SysRole role : userInfo.getRoleList())
        {
            Map<String, Integer> map = new HashMap<>();
            map.put("uid", userInfo.getId());
            map.put("rid", role.getId());
            userInfoDao.addUR(map);
        }
        return new StatusCode(2000, "管理员修改成功");
    }

    @Override
    public UserInfo findUserRoleList(UserInfo userInfo)
    {
        return userInfoDao.findByUserId(userInfo);
    }


}
