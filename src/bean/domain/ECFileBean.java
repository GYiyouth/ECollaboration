package bean.domain;

import smallTools.StringCheck;
import smallTools.Time;
import smallTools.TimeImpl;

import java.util.Date;

/**
 * 约定优先级，工程实践1，比赛2，个人兴趣3，头像等信息4
 * Created by geyao on 2016/11/8.
 */
public class ECFileBean {
	private Integer id = null;
	private String fileName = null;
	private String createDate = null;
	private String deadDate = null;
	private Integer downLoadTimes = null;
	private Integer priority = null;
	private Integer creatorId = null;
	private String path = null;

	public ECFileBean() {
		super();
//		Time time = new TimeImpl();
//		createDate = time.getDateStr();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getDeadDate() {
		return deadDate;
	}

	public void setDeadDate(String deadDate) {
		this.deadDate = deadDate;
	}

	public Integer getDownLoadTimes() {
		return downLoadTimes;
	}

	public void setDownLoadTimes(Integer downLoadTimes) {
		this.downLoadTimes = downLoadTimes;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "ECFileBean{" +
				"id=" + id +
				", fileName='" + fileName + '\'' +
				", createDate=" + createDate +
				", deadDate=" + deadDate +
				", downLoadTimes=" + downLoadTimes +
				", priority=" + priority +
				", creatorId=" + creatorId +
				", path='" + path + '\'' +
				'}';
	}
}
