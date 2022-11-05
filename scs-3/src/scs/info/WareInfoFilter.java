package scs.info;

public class WareInfoFilter implements IInfoFilter {
	@Override
	public String filer(InfoEntry entry) {
		StringBuilder buffer = new StringBuilder();

		buffer.append(entry.getEntry("ID")).append(" ");
		buffer.append(entry.getEntry("Name"));

		return buffer.toString();
	}
}
