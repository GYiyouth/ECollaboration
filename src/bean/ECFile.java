package bean;

import java.util.Date;

/**
 * Created by geyao on 2016/11/8.
 */
public class ECFile {
	private Integer id = null;
	private String fileName = null;
	private Date createDate = null;
	private Date deadDate = null;
	private Integer downLoadTimes = null;
	private Integer priority = null;
	private String path = null;

	public ECFile() {
		super();
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getDeadDate() {
		return deadDate;
	}

	public void setDeadDate(Date deadDate) {
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
