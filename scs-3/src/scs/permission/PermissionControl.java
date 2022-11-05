package scs.permission;

import java.util.HashSet;

public class PermissionControl {
	private HashSet<Permission> permissions = new HashSet<>();	// HashSet's elements are unique.

	public void addPermission(Permission permission) {
		permissions.add(permission);
	}

	public void removePermission(Permission permission) {
		// It doesn't matter if it doesn't have such permission.
		permissions.remove(permission);
	}

	public boolean hasPermission(Permission permission) {
		return permissions.contains(permission);
	}
}