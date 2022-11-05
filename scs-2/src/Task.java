import javax.sound.sampled.ReverbType;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task extends Attachment {
	Date startTime;
	Date endTime;
	int receiveNum;

	public Task(String id, String name, User owner, Course course, Date startTime, Date endTime) {
		super(id, name, owner, course);
		this.startTime = startTime;
		this.endTime = endTime;
		receiveNum = 0;
	}

	@Override
	public String getInfo() {
		StringBuffer buffer = new StringBuffer();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");

		buffer.append("[ID:" + id + "]");
		buffer.append(" [Name:" + name + "]");
		buffer.append(" [ReceiveNum:" + receiveNum + "]");
		buffer.append(" [StartTime:" + format.format(startTime) + "]");
		buffer.append(" [EndTime:" + format.format(endTime) + "]");

		return buffer.toString();
	}

	public Date getStartTime() {
		return startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public int getReceiveNum() {
		return receiveNum;
	}
}
