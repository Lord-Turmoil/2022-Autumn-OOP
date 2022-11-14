package scs.command;

import scs.Host;
import scs.Platform;
import scs.att.Course;
import scs.common.ErrorType;
import scs.permission.Permission;
import scs.user.User;
import scs.vm.VirtualMachine;
import scs.vm.VirtualMachineDocker;
import scs.vm.VirtualMachinePool;

import java.util.Vector;

public class StartVMCommand implements ICommand {
	/**
	 * Handle startVM command.
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
		if (!user.hasPermission(Permission.StartVM)) {
			return ErrorType.PERMISSION_DENIED;
		}

		Course course = platform.getActiveCourse();
		if (course == null) {
			return ErrorType.NO_COURSE_SELECTED;
		}

		VirtualMachinePool pool = VirtualMachineDocker.getInstance().getPool(course.getId());
		VirtualMachine vm = pool.getVirtualMachine(user.getId());
		if (vm == null) {
			return ErrorType.NO_VM;
		}

		runVirtualMachine(vm);

		return ErrorType.SUCCESS;
	}

	private void runVirtualMachine(VirtualMachine vm) {
		Host host = Host.getInstance();
		String input;

		System.out.println(vm.onStartup());

		while (true) {
			input = host.peekInput();
			if ("EOF".equals(input)) {
				break;
			}
			vm.addInput(input);
		}

		System.out.println(vm.onShutdown());
	}
}
