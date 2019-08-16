package top.lijunliang.permission.dao;

import org.springframework.stereotype.Repository;
import top.lijunliang.permission.entity.SysPermission;

import java.util.List;

@Repository
public interface PermissionDao
{
    void add(SysPermission permission);

    void delete(SysPermission permission);

    void update(SysPermission permission);

    SysPermission findByPermissionId(SysPermission permission);

    List<SysPermission> selectAll();
}
