import java.util.HashMap;
import java.util.HashSet;

enum Permission {
	PrintInfo
}

class PermissionControl {
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

class PermissionManager {
	static HashMap<UserType, PermissionControl> permissions = new HashMap<>();

	static {
		for (UserType type : UserType.values()) {
			permissions.put(type, new PermissionControl());
		}

		permissions.get(UserType.Professor).addPermission(Permission.PrintInfo);
	}

	static PermissionControl getPermissionControl(UserType type) {
		return permissions.get(type);
	}
}