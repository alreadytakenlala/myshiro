package top.lijunliang.permission.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lijunliang.permission.dao.MenuDao;
import top.lijunliang.permission.entity.Menu;
import top.lijunliang.permission.service.MenuService;
import top.lijunliang.permission.utils.StatusCode;

import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService
{
    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> selectAll()
    {
        return menuDao.selectAll();
    }

    @Override
    public StatusCode deleteMenu(Menu menu)
    {
        menuDao.delete(menu);
        return new StatusCode(2000, "删除记录成功");
    }
}
