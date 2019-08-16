package top.lijunliang.permission.dao;

import org.springframework.stereotype.Repository;
import top.lijunliang.permission.entity.SysPermission;
import top.lijunliang.permission.entity.SysRole;

import java.util.List;
import java.util.Map;

@Repository
public interface RoleDao
{
    int addR(SysRole role); //添加角色表

    void addRP(Map<String, Integer> map); //添加角色权限关联表

    void delete(SysRole role);

    void deleteRP(SysRole role);

    void updateR(SysRole role);

    List<SysRole> listRoleIN();

    List<SysRole> selectAll();

    SysRole findByRoleId(SysRole role);

    SysRole findByRoleName(SysRole role);
}
