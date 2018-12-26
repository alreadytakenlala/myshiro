package top.lijunliang.permission.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 角色类
 */
public class SysRole implements Serializable
{
    private Integer id;

    private String name;

    private List<SysPermission> permissionList;

    private List<UserInfo> users;

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

    public List<SysPermission> getPermissionList()
    {
        return permissionList;
    }

    public void setPermissionList(List<SysPermission> permissionList)
    {
        this.permissionList = permissionList;
    }

    public List<UserInfo> getUsers()
    {
        return users;
    }

    public void setUsers(List<UserInfo> users)
    {
        this.users = users;
    }

    public SysRole() {}

    public SysRole(Integer id)
    {
        this.id = id;
    }

    public SysRole(String name, List<SysPermission> permissionList)
    {
        this.name = name;
        this.permissionList = permissionList;
    }

    public SysRole(Integer id, String name, List<SysPermission> permissionList)
    {
        this.id = id;
        this.name = name;
        this.permissionList = permissionList;
    }
}
