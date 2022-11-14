package scs.command;

import scs.Platform;
import scs.att.Course;
import scs.common.ErrorType;
import scs.permission.Permission;
import scs.user.User;
import scs.vm.VirtualMachine;
import scs.vm.VirtualMachineDocker;
import scs.vm.VirtualMachinePool;

import java.util.LinkedList;
import java.util.Vector;

public class LogVMCommand implements ICommand {
	/**
	 * Handle logVM command.
	 *
	 * @param args none
	 * @return Handle result.
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() != 0) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		Platform platform = Platform.getInstance();
		User user = platform.getActiveUser();
		if (user == null) {
			return ErrorType.NOT_LOGGED_IN;
		}
		if (!user.hasPermission(Permission.LogVM)) {
			return ErrorType.PERMISSION_DENIED;
		}
		Course course = platform.getActiveCourse();
		if (course == null) {
			return ErrorType.NO_COURSE_SELECTED;
		}

		VirtualMachinePool pool = VirtualMachineDocker.getInstance().getPool(course.getId());
		VirtualMachine vm = pool.getVirtualMachine(user.getId());

		logInputs(vm);

		return ErrorType.SUCCESS;
	}

	private void logInputs(VirtualMachine vm) {
		if (vm == null) {
			System.out.println("no log");
			return;
		}

		LinkedList<String> inputs = vm.getInputs();

		if (inputs.isEmpty()) {
			System.out.println("no log");
		} else {
			for (String input : inputs) {
				System.out.println(input);
			}
		}
	}
}
