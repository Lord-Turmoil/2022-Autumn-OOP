import java.util.HashMap;
import java.util.HashSet;

enum Permission {
	PrintInfo,
	AddCourse, RemoveCourse, ListCourse, SelectCourse,
	AddWare, RemoveWare, ListWare,
	AddTask, RemoveTask, ListTask,
	AddAdmin, RemoveAdmin, ListAdmin,
	AddStudent, RemoveStudent, ListStudent,
	ChangeRole
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
		PermissionControl control;

		for (UserType type : UserType.values()) {
			permissions.put(type, new PermissionControl());
		}

		// Professor.
		control = permissions.get(UserType.Professor);
		control.addPermission(Permission.PrintInfo);

		control.addPermission(Permission.AddCourse);
		control.addPermission(Permission.RemoveCourse);
		control.addPermission(Permission.ListCourse);
		control.addPermission(Permission.SelectCourse);

		control.addPermission(Permission.AddAdmin);
		control.addPermission(Permission.RemoveAdmin);
		control.addPermission(Permission.ListAdmin);

		control.addPermission(Permission.AddWare);
		control.addPermission(Permission.RemoveWare);
		control.addPermission(Permission.ListWare);

		control.addPermission(Permission.AddTask);
		control.addPermission(Permission.RemoveTask);
		control.addPermission(Permission.ListTask);

		control.addPermission(Permission.AddStudent);
		control.addPermission(Permission.RemoveStudent);
		control.addPermission(Permission.ListStudent);

		// Assistant
		control = permissions.get(UserType.Assistant);
		control.addPermission(Permission.SelectCourse);

		control.addPermission(Permission.ListAdmin);

		control.addPermission(Permission.ListWare);

		control.addPermission(Permission.AddTask);
		control.addPermission(Permission.RemoveTask);
		control.addPermission(Permission.ListTask);

		control.addPermission(Permission.AddStudent);
		control.addPermission(Permission.RemoveStudent);
		control.addPermission(Permission.ListStudent);

		control.addPermission(Permission.ChangeRole);

		// Student finally!
		control = getPermissionControl(UserType.Student);
		control.addPermission(Permission.ChangeRole);
	}

	static PermissionControl getPermissionControl(UserType type) {
		return permissions.get(type);
	}
}