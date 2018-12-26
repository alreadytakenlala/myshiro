package top.lijunliang.permission.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 用户类
 */
public class UserInfo implements Serializable
{
    private Integer id;

    private String name;

    private String password;

    private String salt;

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

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public List<SysRole> getRoleList()
    {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList)
    {
        this.roleList = roleList;
    }

    public String getSalt()
    {
        return salt;
    }

    public void setSalt(String salt)
    {
        this.salt = salt;
    }

    public UserInfo() {}

    public UserInfo(String name)
    {
        this.name = name;
    }

    public UserInfo(Integer id, String name, List<SysRole> roleList)
    {
        this.id = id;
        this.name = name;
        this.roleList = roleList;
    }

    public UserInfo(String name, String password, List<SysRole> roleList)
    {
        this.name = name;
        this.password = password;
        this.roleList = roleList;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
