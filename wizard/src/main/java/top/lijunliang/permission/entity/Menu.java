package top.lijunliang.permission.entity;

import java.util.Date;

public class Menu
{
    private String id;

    private String name;

    private String permission;

    private Date time;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
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

    public Date getTime()
    {
        return time;
    }

    public void setTime(Date time)
    {
        this.time = time;
    }

    public Menu() {}

    public Menu(String id, String name, String permission)
    {
        this.id = id;
        this.name = name;
        this.permission = permission;
    }
}
