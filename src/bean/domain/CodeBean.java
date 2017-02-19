package bean.domain;

import smallTools.Time;
import smallTools.TimeImpl;

/**
 * Created by geyao on 2016/11/8.
 */
public class CodeBean {
	private Integer id = null;
	private String codeName = null;
	private Integer row = null;
	private String createDate = null;
	private String deadDate = null;
	private Integer downLoadTimes = null;
	private Integer score = null;
	private String path = null;
	private Integer studentId = null;
	private Integer teamId = null;
	private Integer projectId = null;

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public CodeBean() {
		super();
		Time time = new TimeImpl();
		this.createDate = time.getDateStr();
		this.deadDate = time.getDeadTime();
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
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

	@Override
	public String toString() {
		return "CodeBean{" +
				"id=" + id +
				", codeName='" + codeName + '\'' +
				", row=" + row +
				", createDate='" + createDate + '\'' +
				", deadDate='" + deadDate + '\'' +
				", downLoadTimes=" + downLoadTimes +
				", score=" + score +
				", path='" + path + '\'' +
				", studentId=" + studentId +
				", teamId=" + teamId +
				", projectId=" + projectId +
				'}';
	}
}
