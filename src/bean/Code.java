package bean;

import java.util.Date;

/**
 * Created by geyao on 2016/11/8.
 */
public class Code {
	private Integer id = null;
	private Integer row = null;
	private Date createDate = null;
	private Date deadDate = null;
	private Integer downLoadTimes = null;
	private Integer score = null;
	private String path = null;

	public Code() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
