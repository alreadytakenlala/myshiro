package top.lijunliang.permission.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lijunliang.permission.dao.PermissionDao;
import top.lijunliang.permission.entity.SysPermission;
import top.lijunliang.permission.service.PermissionService;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService
{
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<SysPermission> listPermission()
    {
        return permissionDao.selectAll();
    }
}
