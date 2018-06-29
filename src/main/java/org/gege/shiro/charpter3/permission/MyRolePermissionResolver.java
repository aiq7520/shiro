package org.gege.shiro.charpter3.permission;

import java.util.Collection;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;

public class MyRolePermissionResolver implements RolePermissionResolver {

	@Override
	public Collection<Permission> resolvePermissionsInRole(String roleString) {
		return null;
	}
}
