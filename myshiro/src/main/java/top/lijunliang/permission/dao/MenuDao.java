package top.lijunliang.permission.dao;

import org.springframework.stereotype.Repository;
import top.lijunliang.permission.entity.Menu;

import java.util.List;

@Repository
public interface MenuDao
{
    void add(Menu menu);

    void delete(Menu menu);

    List<Menu> selectAll();
}
