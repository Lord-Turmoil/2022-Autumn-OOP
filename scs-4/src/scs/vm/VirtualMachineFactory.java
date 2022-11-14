package scs.vm;

import java.awt.*;

public class VirtualMachineFactory {
	static VirtualMachine getMachine(String description) {
		if ("Windows".equals(description)) {
			return new WindowsMachine();
		} else if ("Linux".equals(description)) {
			return new LinuxMachine();
		} else if ("MacOS".equals(description)) {
			return new MacOSMachine();
		}

		return null;
	}
}
