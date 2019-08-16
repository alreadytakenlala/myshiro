package top.lijunliang.permission.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 权限类
 */
public class SysPermission implements Serializable
{
    private Integer id;

    private String name;

    private String permission;

    private List<SysRole> roleList;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPermission()
    {
        return permission;
    }

    public void setPermission(String permission)
    {
        this.permission = permission;
    }

    public List<SysRole> getRoleList()
    {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList)
    {
        this.roleList = roleList;
    }

    public SysPermission() {}

    public SysPermission(Integer id)
    {
        this.id = id;
    }
}
