package scs.vm;

import scs.common.ErrorType;
import scs.util.Serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

/**
 * Hmm... Maintains a map between student and their virtual machine.
 */
public class VirtualMachinePool {
	VirtualMachineDispatcher dispatcher = new VirtualMachineDispatcher();
	HashMap<String, VirtualMachine> records = new HashMap<>();
	Vector<VirtualMachine> pool = new Vector<>();	// Barnacles.

	public ErrorType assignLocal(String studentID, String description) {
		return assign(studentID, dispatcher.requestLocal(description));
	}

	public ErrorType assignRemote(String studentId, String dir) {
		return assign(studentId, dispatcher.requestRemote(dir));
	}

	private ErrorType assign(String studentId, VirtualMachine vm) {
		if (vm == null) {
			return ErrorType.FAILED_TO_ASSIGN_VIRTUAL_MACHINE;
		}

		// Old one will be replaced by default.
		records.put(studentId, vm);
		pool.add(vm);

		return ErrorType.SUCCESS;
	}

	public ErrorType upload(String studentId, String dir) {
		VirtualMachine vm = getVirtualMachine(studentId);
		if (vm == null) {
			return ErrorType.NO_VIRTUAL_MACHINE;
		}

		ErrorType error = Serializer.serialize(vm, dir);
		if (error != ErrorType.SUCCESS) {
			return ErrorType.FAILED_TO_SERIALIZE;
		}

		return ErrorType.SUCCESS;
	}

	public VirtualMachine getVirtualMachine(String studentId) {
		return records.get(studentId);
	}

	public VirtualMachine getVirtualMachine(int vmId) {
		if ((vmId < 1) || (vmId > pool.size())) {
			return null;
		}

		return pool.elementAt(vmId - 1);
	}
}
