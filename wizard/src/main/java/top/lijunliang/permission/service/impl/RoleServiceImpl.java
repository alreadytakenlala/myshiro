package top.lijunliang.permission.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lijunliang.permission.dao.MenuDao;
import top.lijunliang.permission.dao.RoleDao;
import top.lijunliang.permission.entity.Menu;
import top.lijunliang.permission.entity.SysPermission;
import top.lijunliang.permission.entity.SysRole;
import top.lijunliang.permission.service.RoleService;
import top.lijunliang.permission.utils.CodeUtil;
import top.lijunliang.permission.utils.StatusCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RoleServiceImpl implements RoleService
{
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private MenuDao menuDao;

    @Override
    public StatusCode addRole(SysRole sysRole)
    {
        menuDao.add(new Menu(CodeUtil.createRandomNumber(), "添加", "role:add"));
        roleDao.addR(sysRole);

        for (SysPermission sysPermission : sysRole.getPermissionList())
        {
            Map<String, Integer> map = new HashMap<>();
            Integer rid = sysRole.getId();
            Integer pid = sysPermission.getId();
            map.put("rid", rid);
            map.put("pid", pid);
            roleDao.addRP(map);
        }
        return new StatusCode(2000, "角色提交成功");
    }

    @Override
    public List<SysRole> listRoleIN()
    {
        return roleDao.listRoleIN();
    }

    @Override
    public List<SysRole> selectAll()
    {
        return roleDao.selectAll();
    }

    @Override
    public SysRole findRole(SysRole role)
    {
        return roleDao.findByRoleId(role);
    }

    @Override
    public StatusCode deleteRole(SysRole sysRole)
    {
        menuDao.add(new Menu(CodeUtil.createRandomNumber(), "删除", "role:delete"));
        roleDao.delete(sysRole);
        return new StatusCode(2000, "角色删除成功");
    }

    @Override
    public StatusCode updateRole(SysRole role)
    {
        menuDao.add(new Menu(CodeUtil.createRandomNumber(), "更新", "role:update"));
        roleDao.deleteRP(role);
        roleDao.updateR(role);

        for (SysPermission sysPermission : role.getPermissionList())
        {
            Map<String, Integer> map = new HashMap<>();
            Integer rid = role.getId();
            Integer pid = sysPermission.getId();
            map.put("rid", rid);
            map.put("pid", pid);
            roleDao.addRP(map);
        }
        return new StatusCode(2000, "角色修改成功");
    }
}
