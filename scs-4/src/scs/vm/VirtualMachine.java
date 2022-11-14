package scs.vm;

import java.io.Serializable;
import java.util.LinkedList;

public abstract class VirtualMachine implements Serializable {
	private VirtualMachineType type;
	private LinkedList<String> inputs = new LinkedList<>();
	private transient int id;	// no need for serialization

	public VirtualMachine(VirtualMachineType type) {
		this.type = type;
		this.id = 0;
	}

	// Only VMDispatcher can access this.
	protected void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public VirtualMachineType getType() {
		return type;
	}

	public LinkedList<String> getInputs() {
		return inputs;
	}

	public void addInput(String input) {
		inputs.add(input);
	}

	public void clearInputs() {
		inputs.clear();
	}

	public String onStartup() {
		return "welcome to " + type;
	}

	public String onShutdown() {
		return "quit " + type;
	}
}
