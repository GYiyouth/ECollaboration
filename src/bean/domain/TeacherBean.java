package bean.domain;


public class TeacherBean {
	private Integer id = null;
	private String homePageUrl = null;
	private Integer needStudentsFlag = null;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHomePageUrl() {
		return homePageUrl;
	}

	public void setHomePageUrl(String homePageUrl) {
		this.homePageUrl = homePageUrl;
	}

	public Integer getNeedStudentsFlag() {
		return needStudentsFlag;
	}

	public void setNeedStudentsFlag(Integer needStudentsFlag) {
		this.needStudentsFlag = needStudentsFlag;
	}
}
