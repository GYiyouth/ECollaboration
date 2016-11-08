package bean;

import java.util.Date;

/**
 * Created by geyao on 2016/11/7.
 */
public class Project {
	private Integer id = null;
	private String name = null;
	private Date endDate = null;
	private Date finishDate = null;
	private Date survivalDate = null;
	private Integer teamNumber = null;
	private Integer teamMax = null;
	private Integer memberMax = null;
	private Date createDate = null;
	private Integer grade = null;
	private String keyWord = null;
	private String info = null;
	private String require = null;
	private String gain = null;
	private Integer priority = null;
	private Integer status = null;
	private Integer creatorId = null;
	private Integer teacherId = null;

	public Project(){
		super();
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public Date getSurvivalDate() {
		return survivalDate;
	}

	public void setSurvivalDate(Date survivalDate) {
		this.survivalDate = survivalDate;
	}

	public Integer getTeamNumber() {
		return teamNumber;
	}

	public void setTeamNumber(Integer teamNumber) {
		this.teamNumber = teamNumber;
	}

	public Integer getTeamMax() {
		return teamMax;
	}

	public void setTeamMax(Integer teamMax) {
		this.teamMax = teamMax;
	}

	public Integer getMemberMax() {
		return memberMax;
	}

	public void setMemberMax(Integer memberMax) {
		this.memberMax = memberMax;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getRequire() {
		return require;
	}

	public void setRequire(String require) {
		this.require = require;
	}

	public String getGain() {
		return gain;
	}

	public void setGain(String gain) {
		this.gain = gain;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
}
