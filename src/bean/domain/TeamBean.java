package bean.domain;

import java.util.Date;

/**
 * 团队bean
 * Created by geyao on 2016/11/7.
 */
public class TeamBean {
	private Integer id = null;
	private String teamName = null;
	private Integer creatorId = null;
	private Date createDate = null;

	public TeamBean() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
