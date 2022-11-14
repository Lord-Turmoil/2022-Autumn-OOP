package scs.vm;

import scs.util.Serializer;

public class VirtualMachineDispatcher {
	private int count = 0;	// dispatched machine count

	/**
	 * Request local virtual machine.
	 * @param description Type of the virtual machine.
	 * @return return a "brand" new machine. :P
	 */
	public VirtualMachine requestLocal(String description) {
		return dispatch(VirtualMachineFactory.getMachine(description));
	}

	/**
	 * Request remote virtual machine. Just deserialize one.
	 * @param dir Where the virtual machine is stored.
	 * @return Deserialized machine.
	 */
	public VirtualMachine requestRemote(String dir) {
		return dispatch((VirtualMachine) Serializer.deserialize(dir));
	}

	private VirtualMachine dispatch(VirtualMachine virtualMachine) {
		if (virtualMachine != null) {
			count++;
			virtualMachine.setId(count);
		}

		return virtualMachine;
	}
}
