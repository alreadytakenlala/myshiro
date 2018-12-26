package top.lijunliang.permission.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.lijunliang.permission.entity.*;
import top.lijunliang.permission.service.*;
import top.lijunliang.permission.utils.StatusCode;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/data")
public class DataController
{
    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/addAdmin")
    @RequiresPermissions("admin:add")
    public StatusCode addAdmin(@RequestParam("name") String name, @RequestParam("password") String password,
                           @RequestParam("rls[]") List<Integer> rls)
    {
        List<SysRole> roleList = new ArrayList<>();
        for (Integer rid : rls)
        {
            roleList.add(new SysRole(rid));
        }
        return userInfoService.addUserInfo(new UserInfo(name, password, roleList));
    }

    @GetMapping("/listUserInfo")
    @RequiresPermissions("admin:select")
    public List<UserInfo> listUserInfo()
    {
        return userInfoService.listUserInfo();
    }

    @PostMapping("/deleteUser")
    @RequiresPermissions("admin:delete")
    public StatusCode deleteUser(UserInfo userInfo)
    {
        return userInfoService.deleteUserInfo(userInfo);
    }

    @PostMapping("/updateUser")
    @RequiresPermissions("admin:update")
    public StatusCode updateUser(@RequestParam("userId") Integer id, @RequestParam("username") String name, @RequestParam("rls[]") List<Integer> rls)
    {
        List<SysRole> rs = new ArrayList<>();
        for (Integer rid : rls)
        {
            rs.add(new SysRole(rid));
        }
        return userInfoService.updateUserInfo(new UserInfo(id, name, rs));
    }

    @RequestMapping("/findUser")
    @RequiresPermissions("admin:select")
    public UserInfo findUser(UserInfo userInfo)
    {
        return userInfoService.findUserRoleList(userInfo);
    }

    @RequestMapping("/listArticle")
    @RequiresPermissions("artiele:select")
    public List<Article> listArticle(HttpServletRequest request)
    {
        return articleService.selectAll();
    }

    @PostMapping("/addArticle")
    @RequiresPermissions("article:add")
    public StatusCode addArticle(Article article)
    {
        return articleService.addArticle(article);
    }

    @PostMapping("/deleteArticle")
    @RequiresPermissions("artiele:delete")
    public StatusCode delteArticle(Article article)
    {
        return articleService.deleteArticle(article);
    }

    @GetMapping("/listMenu")
    @RequiresPermissions("menu:select")
    public List<Menu> selectAll()
    {
        return menuService.selectAll();
    }

    @RequestMapping("/deleteMenu")
    @RequiresPermissions("menu:delete")
    public StatusCode deleteMenu(Menu menu)
    {
        return menuService.deleteMenu(menu);
    }

    @RequestMapping("/listPermission")
    public List<SysPermission> listPermission()
    {
        return permissionService.listPermission();
    }

    @GetMapping("/listRoleIN")
    @RequiresPermissions("role:select")
    public List<SysRole> listRoleIN() //只查询角色id和名字
    {
        return roleService.listRoleIN();
    }

    @GetMapping("/listRole")
    @RequiresPermissions("role:select")
    public List<SysRole> listRole()
    {
        return roleService.selectAll();
    }

    @PostMapping("/findRole")
    @RequiresPermissions("role:select")
    public SysRole findByRoleId(SysRole role)
    {
        return roleService.findRole(role);
    }

    @PostMapping("/deleteRole")
    @RequiresPermissions("role:delete")
    public StatusCode deleteRole(SysRole role)
    {
        return roleService.deleteRole(role);
    }

    @RequestMapping("/addRole")
    @RequiresPermissions("role:add")
    public StatusCode addRole(@RequestParam("roleName") String roleName, @RequestParam("pls[]") Integer[] pls)
    {
        List<SysPermission> ps = new ArrayList<>();
        for (Integer pid : pls)
        {
            ps.add(new SysPermission(pid));
        }
        return roleService.addRole(new SysRole(roleName, ps));
    }

    @PostMapping("/updateRole")
    @RequiresPermissions("role:update")
    public StatusCode updateRole(@RequestParam("roleId") Integer id, @RequestParam("roleName") String roleName, @RequestParam("pls[]") Integer[] pls)
    {
        List<SysPermission> ps = new ArrayList<>();
        for (Integer pid : pls)
        {
            ps.add(new SysPermission(pid));
        }
        return roleService.updateRole(new SysRole(id, roleName, ps));
    }
}
