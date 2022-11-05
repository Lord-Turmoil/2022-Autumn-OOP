package scs.permission;

import scs.user.*;

import java.util.HashMap;

public class PermissionManager {
	static HashMap<UserType, PermissionControl> permissions = new HashMap<>();

	static {
		PermissionControl control;

		for (UserType type : UserType.values()) {
			permissions.put(type, new PermissionControl());
		}

		// platform.user.Professor.
		control = permissions.get(UserType.Professor);
		control.addPermission(Permission.PrintInfo);

		control.addPermission(Permission.AddCourse);
		control.addPermission(Permission.RemoveCourse);
		control.addPermission(Permission.ListCourseAdmin);
		control.addPermission(Permission.ListCourse);
		control.addPermission(Permission.SelectCourse);

		control.addPermission(Permission.AddAdmin);
		control.addPermission(Permission.RemoveAdmin);
		control.addPermission(Permission.ListAdmin);
		control.addPermission(Permission.ListAdminAdmin);

		control.addPermission(Permission.AddWare);
		control.addPermission(Permission.RemoveWare);
		control.addPermission(Permission.ListWare);

		control.addPermission(Permission.AddTask);
		control.addPermission(Permission.RemoveTask);
		control.addPermission(Permission.ListTask);
		control.addPermission(Permission.ListTaskAdmin);
		control.addPermission(Permission.AddAnswer);

		control.addPermission(Permission.AddStudent);
		control.addPermission(Permission.RemoveStudent);
		control.addPermission(Permission.ListStudent);

		control.addPermission(Permission.DownloadFile);
		control.addPermission(Permission.OpenFile);

		control.addPermission(Permission.QueryScoreAdmin);

		// Assistant
		control = permissions.get(UserType.Assistant);

		control.addPermission(Permission.ListCourseAdmin);
		control.addPermission(Permission.ListCourse);
		control.addPermission(Permission.SelectCourse);

		control.addPermission(Permission.ListAdmin);
		control.addPermission(Permission.ListAdminAdmin);

		control.addPermission(Permission.AddWare);
		control.addPermission(Permission.RemoveWare);
		control.addPermission(Permission.ListWare);

		control.addPermission(Permission.AddTask);
		control.addPermission(Permission.RemoveTask);
		control.addPermission(Permission.ListTask);
		control.addPermission(Permission.ListTaskAdmin);
		control.addPermission(Permission.AddAnswer);

		control.addPermission(Permission.AddStudent);
		control.addPermission(Permission.RemoveStudent);
		control.addPermission(Permission.ListStudent);

		control.addPermission(Permission.ChangeRole);

		control.addPermission(Permission.DownloadFile);
		control.addPermission(Permission.OpenFile);

		control.addPermission(Permission.QueryScoreAdmin);

		// Student finally!
		control = getPermissionControl(UserType.Student);
		control.addPermission(Permission.ChangeRole);

		control.addPermission(Permission.ListCourse);
		control.addPermission(Permission.SelectCourse);

		control.addPermission(Permission.ListAdmin);
		control.addPermission(Permission.ListWare);

		control.addPermission(Permission.ListTask);
		control.addPermission(Permission.SubmitTask);

		control.addPermission(Permission.DownloadFile);
		control.addPermission(Permission.OpenFile);

		control.addPermission(Permission.QueryScore);
	}

	public static PermissionControl getPermissionControl(UserType type) {
		return permissions.get(type);
	}
}