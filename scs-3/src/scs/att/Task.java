package scs.att;

import scs.info.InfoEntry;
import scs.user.User;
import scs.util.FileUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class Task extends Attachment {
	private Date startTime;
	private Date endTime;
	private int receivedNum;
	private HashMap<String, Submission> received = new HashMap<>();

	public Task(String id, String name, User owner, Course course, Date startTime, Date endTime) {
		super("tasks", id, name, owner, course);
		this.startTime = startTime;
		this.endTime = endTime;
		receivedNum = 0;
		filename = name;
	}

	@Override
	public InfoEntry getInfo() {
		InfoEntry entry = new InfoEntry();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");

		entry.add("ID", id).add("Name", name);
		entry.add("SubmissionStatus",
				Integer.toString(receivedNum) + "/" + Integer.toString(course.getStudentNum()));
		entry.add("StartTime", format.format(startTime));
		entry.add("EndTime", format.format(endTime));

		return entry;
	}

	// For students.
	public InfoEntry getInfo(String id) {
		InfoEntry entry = getInfo();

		entry.add("Status", isSubmitted(id) ? "done" : "undone");

		return entry;
	}


	public Date getStartTime() {
		return startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public int getReceivedNum() {
		return receivedNum;
	}

	public void submit(String id, Submission submission) {
		if (!received.containsKey(id)) {
			received.put(id, submission);
			receivedNum++;
		} else {
			double old = received.get(id).getScore();
			if (old < submission.getScore()) {
				received.replace(id, submission);
			}
		}
	}

	public boolean isSubmitted(String id) {
		return received.containsKey(id);
	}

	public Submission getSubmission(String id) {
		return received.get(id);
	}
	public Vector<Submission> getSubmission() {
		Vector<Submission> result = new Vector<>();

		for (Submission submission : received.values()) {
			result.add(submission);
		}

		return result;
	}

	public String getAnswerFilename() {
		return FileUtil.joint(getAnswerDirectory(), id + ".ans");
	}

	public String getAnswerDirectory() {
		return FileUtil.joint(course.getDirectory(), "answers");
	}
}
