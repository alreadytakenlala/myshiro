package top.lijunliang.permission.service;

import top.lijunliang.permission.entity.SysRole;
import top.lijunliang.permission.utils.StatusCode;

import java.util.List;

public interface RoleService
{
    public StatusCode addRole(SysRole role);

    public List<SysRole> listRoleIN();

    public List<SysRole> selectAll();

    public SysRole findRole(SysRole role);

    public StatusCode deleteRole(SysRole role);

    public StatusCode updateRole(SysRole role);
}
