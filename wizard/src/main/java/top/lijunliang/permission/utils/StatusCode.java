package top.lijunliang.permission.utils;

import java.io.Serializable;

/**
 * 状态类
 */
public class StatusCode implements Serializable
{
    private int status;

    private String msg;

    private String cookies;

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public String getCookies()
    {
        return cookies;
    }

    public void setCookies(String cookies)
    {
        this.cookies = cookies;
    }

    public StatusCode()
    {
    }

    public StatusCode(Integer status, String msg)
    {
        this.status = status;
        this.msg = msg;
    }

    public StatusCode(Integer status, String msg, String cookies)
    {
        this.status = status;
        this.msg = msg;
        this.cookies = cookies;
    }
}
