package bean.domain;


public class TeacherBean {
	private Integer id = null;
	private String staffid = null;
	private String name = null;
	private String homePageUrl = null;
	private Integer needStudentsFlag = null;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStaffid() {
		return staffid;
	}

	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
