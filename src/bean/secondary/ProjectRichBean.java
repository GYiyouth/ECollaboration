package bean.secondary;

import bean.domain.ProjectBean;
import bean.domain.TeamBean;

import java.util.ArrayList;

/**
 * Created by geyao on 2017/1/4.
 */
public class ProjectRichBean extends ProjectBean {
	private String creatorName;
	private ArrayList<TeamBean> teamBeans = new ArrayList<>();
	public ProjectRichBean(){
		super();
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public ArrayList<TeamBean> getTeamBeens() {
		return teamBeans;
	}

	public void setTeamBeens(ArrayList<TeamBean> teamBeens) {
		this.teamBeans = teamBeens;
	}
}
