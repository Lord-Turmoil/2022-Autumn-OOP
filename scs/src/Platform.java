import java.util.HashMap;

public class Platform {
	private static Platform instance = null;

	// id - User
	private HashMap<String, User> users = new HashMap<>();
	private User activeUser = null;

	public static Platform getInstance() {
		if (instance == null)
			instance = new Platform();
		return instance;
	}

	public boolean hasUser(String id) {
		return users.containsKey(id);
	}

	public User getActiveUser() {
		return activeUser;
	}

	public User getUser(String id) {
		return users.get(id);
	}

	/*
	 * Register and log related methods. All premises MUST be checked
	 * before.
	 */
	public boolean register(User user) {
		if (user == null) {
			return false;
		}

		if (hasUser(user.getId())) {
			return false;
		}

		users.put(user.getId(), user);

		return true;
	}

	public boolean remove(String id) {
		if (!hasUser(id)) {
			return false;
		}

		users.remove(id);

		return true;
	}

	public boolean login(String id) {
		if (activeUser != null) {
			return false;
		}

		activeUser = getUser(id);

		return activeUser != null;
	}

	public boolean logout() {
		if (activeUser == null) {
			return false;
		}

		activeUser = null;

		return true;
	}
}