package scs.att;

import jdk.jfr.BooleanFlag;
import scs.Platform;
import scs.common.Global;
import scs.info.InfoEntry;
import scs.util.FileUtil;

import java.text.DecimalFormat;

public class Submission extends Content {
	private Task task;	// parent task
	private double score;

	/**
	 * Constructor of the object.
	 * @param id student id
	 */
	public Submission(String id, Task task) {
		super(id, id + ".task");
		this.task = task;
		score = Global.INVALID_SCORE;
	}

	public Submission(String id, Task task, int score) {
		super(id, id);
		this.task = task;
		this.score = score;
	}

	@Override
	public InfoEntry getInfo() {
		InfoEntry entry = new InfoEntry();
		entry.add("ID", id);
		entry.add("Name", Platform.getInstance().getUser(id).getName());
		entry.add("Task_ID", task.getId());
		entry.add("Score", getScoreString());

		return entry;
	}

	public void setScore(double score) {
		if (score != Global.INVALID_SCORE) {
			if (score > this.score) {
				this.score = score;
			}
		}
	}

	public Task getTask() {
		return task;
	}

	public double getScore() {
		return score;
	}

	public String getScoreString() {
		if (score == Global.INVALID_SCORE) {
			return "None";
		} else {
			return String.format("%.1f", score);
		}
	}

	@Override
	public String getDirectory() {
		return task.getDirectory();
	}

	@Override
	public String getFilename() {
		return FileUtil.joint(getDirectory(), name);
	}
}
