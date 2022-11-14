package scs.vm;

import java.util.HashMap;

/**
 * Hmm... Keep all virtual machines. Maintains a map between
 * course and VirtualMachinePool.
 * A pool for a course is only created when get pool method
 * is called for it. Just lazy singleton, like that, I think.
 */
public class VirtualMachineDocker {
	private static VirtualMachineDocker instance = new VirtualMachineDocker();
	public static VirtualMachineDocker getInstance() {
		return instance;
	}

	HashMap<String, VirtualMachinePool> pools = new HashMap<>();

	public VirtualMachinePool getPool(String id) {
		if (pools.containsKey(id)) {
			return pools.get(id);
		}

		VirtualMachinePool pool = new VirtualMachinePool();
		pools.put(id, pool);

		return pool;
	}
}
