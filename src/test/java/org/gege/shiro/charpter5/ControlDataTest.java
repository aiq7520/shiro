package org.gege.shiro.charpter5;

import org.apache.shiro.util.ThreadContext;
import org.gege.shiro.charpter5.entity.Permission;
import org.gege.shiro.charpter5.entity.Role;
import org.gege.shiro.charpter5.entity.User;
import org.gege.shiro.charpter5.service.PermissionService;
import org.gege.shiro.charpter5.service.RoleService;
import org.gege.shiro.charpter5.service.UserServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("/shiro/charpter5/spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ControlDataTest {
	
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserServices userService;

    protected String password = "123";

    protected Permission p1;
    protected Permission p2;
    protected Permission p3;
    protected Role r1;
    protected Role r2;
    protected User u1;
    protected User u2;
    protected User u3;
    protected User u4;

    @Test
    public void createData() {
    	//1、新增权限
    	  p1 = new Permission("user:create", "用户模块新增", Boolean.TRUE);
          p2 = new Permission("user:update", "用户模块修改", Boolean.TRUE);
          p3 = new Permission("menu:create", "菜单模块新增", Boolean.TRUE);
          permissionService.createPermission(p1);
          permissionService.createPermission(p2);
          permissionService.createPermission(p3);
       
        //2、新增角色
        r1 = new Role("admin", "管理员", Boolean.TRUE);
        r2 = new Role("user", "用户管理员", Boolean.TRUE);
        roleService.save(r1);
        roleService.save(r2);
        //3、关联角色-权限
        roleService.correlationPermissions(r1.getId(), p1.getId());
        roleService.correlationPermissions(r1.getId(), p2.getId());
        roleService.correlationPermissions(r1.getId(), p3.getId());

        roleService.correlationPermissions(r2.getId(), p1.getId());
        roleService.correlationPermissions(r2.getId(), p2.getId());

        //4、新增用户
        u1 = new User("zhang", password);
        u2 = new User("li", password);
        u3 = new User("wu", password);
        u4 = new User("wang", password);
        u4.setLocked(Boolean.TRUE);
        userService.save(u1);
        userService.save(u2);
        userService.save(u3);
        userService.save(u4);
        //5、关联用户-角色
        userService.correlationRoles(u1.getId(), r1.getId());
        userService.correlationRoles(u2.getId(), r2.getId());
        ThreadContext.unbindSubject();//退出时请解除绑定Subject到线程 否则对下次测试造成影响
    }
}
