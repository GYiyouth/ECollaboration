package bean.domain;

import javax.persistence.*;

/**
 * Created by geyao on 2016/11/7.
 */
@Entity
@Table(name = "project")
public class ProjectBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id = null;
	private String name = null;

	private String applyBeforeDate = null;
	private String finishDate = null;
	private String survivalDate = null;
	private Integer teamNumber = null;
	private Integer teamMax = null;
	private Integer memberMax = null;
	private String createDate = null;
	private Integer grade = null;
	private String keyWord = null;
	private String info = null;
	private String requirement = null;
	private String gain = null;
	private Integer priority = null;
	private Integer status = null;
	private Integer creatorId = null;
	private Integer teacherId = null;

	public ProjectBean(){
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

	public String getApplyBeforeDate() {
		return applyBeforeDate;
	}

	public void setApplyBeforeDate(String applyBeforeDate) {
		this.applyBeforeDate = applyBeforeDate;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public String getSurvivalDate() {
		return survivalDate;
	}

	public void setSurvivalDate(String survivalDate) {
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
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

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
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

	@Override
	public String toString() {
		return "ProjectBean{" +
				"id=" + id +
				", name='" + name + '\'' +
				", applyBeforeDate='" + applyBeforeDate + '\'' +
				", finishDate='" + finishDate + '\'' +
				", survivalDate='" + survivalDate + '\'' +
				", teamNumber=" + teamNumber +
				", teamMax=" + teamMax +
				", memberMax=" + memberMax +
				", createDate='" + createDate + '\'' +
				", grade='" + grade + '\'' +
				", keyWord='" + keyWord + '\'' +
				", info='" + info + '\'' +
				", requirement='" + requirement + '\'' +
				", gain='" + gain + '\'' +
				", priority=" + priority +
				", status=" + status +
				", creatorId=" + creatorId +
				", teacherId=" + teacherId +
				'}';
	}
}
