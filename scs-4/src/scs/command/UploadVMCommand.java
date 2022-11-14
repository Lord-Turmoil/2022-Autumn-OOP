package scs.command;

import scs.Platform;
import scs.att.Course;
import scs.common.ErrorType;
import scs.permission.Permission;
import scs.user.User;
import scs.vm.VirtualMachine;
import scs.vm.VirtualMachineDocker;
import scs.vm.VirtualMachinePool;

import java.util.Vector;

public class UploadVMCommand implements ICommand {
	/**
	 * Handle uploadVM command.
	 * @param args dir
	 * @return Handle result.
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() != 1) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		Platform platform = Platform.getInstance();
		User user = platform.getActiveUser();
		if (user == null) {
			return ErrorType.NOT_LOGGED_IN;
		}
		if (!user.hasPermission(Permission.UploadVM)) {
			return ErrorType.PERMISSION_DENIED;
		}
		Course course = platform.getActiveCourse();
		if (course == null) {
			return ErrorType.NO_COURSE_SELECTED;
		}

		VirtualMachinePool pool = VirtualMachineDocker.getInstance().getPool(course.getId());
		ErrorType error = pool.upload(user.getId(), args.elementAt(0));
		if (error != ErrorType.SUCCESS) {
			return ErrorType.FAILED_TO_UPLOAD_VIRTUAL_MACHINE;
		}

		System.out.println("uploadVM success");

		return ErrorType.SUCCESS;
	}
}
