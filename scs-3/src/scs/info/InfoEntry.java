package scs.info;

import java.util.HashMap;

/**
 * Well, this gathers all information of an object.
 */
public class InfoEntry {
	private HashMap<String, String> entry = new HashMap<>();

	public InfoEntry add(String key, String value) {
		if (!entry.containsKey(key)) {
			entry.put(key, value);
		}

		return this;
	}

	public InfoEntry add(String key, int value) {
		if (!entry.containsKey(key)) {
			entry.put(key, Integer.toString(value));
		}

		return this;
	}

	public String get(String key) {
		return entry.get(key);
	}

	public String getEntry(String key) {
		if (!entry.containsKey(key)) {
			return null;
		}

		StringBuilder buffer = new StringBuilder();
		buffer.append("[");
		buffer.append(key).append(":").append(entry.get(key));
		buffer.append("]");

		return buffer.toString();
	}

	/**
	 * This is deprecated. To replace the original key with alter.
	 * @param key The key of the entry.
	 * @param alter The alternative key.
	 * @return Return filtered entry.
	 */
	public String getEntry(String key, String alter) {
		if (!entry.containsKey(key)) {
			return null;
		}

		StringBuilder buffer = new StringBuilder();
		buffer.append("[");
		buffer.append(alter).append(":").append(entry.get(key));
		buffer.append("]");

		return buffer.toString();
	}
}
