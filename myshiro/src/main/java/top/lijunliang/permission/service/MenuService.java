package top.lijunliang.permission.service;

import top.lijunliang.permission.entity.Menu;
import top.lijunliang.permission.utils.StatusCode;

import java.util.List;

public interface MenuService
{
    List<Menu> selectAll();

    StatusCode deleteMenu(Menu menu);
}
