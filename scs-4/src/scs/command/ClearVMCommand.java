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

public class ClearVMCommand implements ICommand {
	/**
	 * Handle clearVM command.
	 * @param args VM's ID
	 * @return
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
		if (!user.hasPermission(Permission.ClearVM)) {
			return ErrorType.PERMISSION_DENIED;
		}
		Course course = platform.getActiveCourse();
		if (course == null) {
			return ErrorType.NO_COURSE_SELECTED;
		}

		int id = Integer.parseInt(args.elementAt(0));
		VirtualMachinePool pool = VirtualMachineDocker.getInstance().getPool(course.getId());
		VirtualMachine vm = pool.getVirtualMachine(id);
		if (vm == null) {
			return ErrorType.NO_VM;
		}

		vm.clearInputs();
		System.out.println("clear " + vm.getType() + " success");

		return ErrorType.SUCCESS;
	}
}
