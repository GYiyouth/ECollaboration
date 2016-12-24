package actions.file;

import DAO.ECFileDAO.ECFileDAO;
import DAO.ECFileDAO.ECFileDAOImpl;
import DAO.studentDAO.StudentDAO;
import DAO.studentDAO.StudentDaoImpl;
import bean.BusinessBean.File.FileIOBean;
import bean.domain.ECFileBean;
import bean.domain.UserBean;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import smallTools.Time;
import smallTools.TimeImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

/**
 * 上传文件的action
 * 要求session里有userId，userBean等东西，已经加在了登录的action里
 * 如果是学生上传文件，form表单里必须要有projectId和teamId。
 * Created by geyao on 2016/12/24.
 */
public class fileUploadAction implements SessionAware, ServletRequestAware, ServletResponseAware {
	private Map session;
	private ECFileBean fileBean = new ECFileBean();
	private Integer creatorId;
	private String savePath;
	private Integer fileId;
	private Integer projectId;
	private Integer teamId;
	private HttpServletRequest request;
	private HttpServletResponse response;

	//注意，file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
	private File file;

	//提交过来的file的名字
	private String fileFileName;

	//提交过来的file的MIME类型
	private String fileContentType;

	//仅用作学生上传学校工程实践项目的文件
	public String studentUploadFile(){
		if (projectId!=null && teamId != null){
			try {
				fileBeanInit("/schoolPractices/" + projectId + "/" +teamId);
				FileIOBean fileIOBean = new FileIOBean();
				fileIOBean.uploadFile( getSavePath(), getFileFileName(), getFile());
				ECFileDAO fileDAO = new ECFileDAOImpl();
				int id = fileDAO.addFile( getFileBean() );
				setFileId(id);
				getFileBean().setId(id);
				UserBean userBean = (UserBean) this.session.get("userBean");
				setCreatorId(userBean.getId());
				StudentDAO studentDAO = new StudentDaoImpl();
				studentDAO.setFile(getCreatorId(), teamId, projectId, getFileId());
			} catch (Exception e) {
				e.printStackTrace();
				return "fail";
			}
			return "success";
		}else
			return "fail";
	}

	//用作老师上传文件，可以精确到项目、团队
	//需要在seesion里放置projectId和teamId的数组,不管精确与否
//	public String teacherUploadFile(){
//		try {
//			UserBean userBean = (UserBean) session.get("userBean");
//			ArrayList<Integer> projectIdList = (ArrayList<Integer>) session.get("projectIdList");
//			ArrayList<Integer> teamIdList = (ArrayList<Integer>) session.get("teamIdList");
//			return "fail";
//		}
//
//	}

	public void fileBeanInit(String path){
		getFileBean().setFileName(getFileFileName());
		Time time = new TimeImpl();
		System.out.println("time的getTime()方法 = " + time.getTime());
		getFileBean().setCreateDate(time.getTime());
		getFileBean().setDeadDate(time.getDeadTime());
		getFileBean().setDownLoadTimes(0);
		getFileBean().setCreatorId( (int)session.get("userId") );
		setSavePath(""+
				ServletActionContext.getServletContext().getRealPath("")+
				"/../../../web/upload" + path
		);
		getFileBean().setPath( getSavePath() );

	}

	/**
	 * Sets the Map of session attributes in the implementing class.
	 *
	 * @param session a Map of HTTP session attribute name/value pairs.
	 */
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public ECFileBean getFileBean() {
		return fileBean;
	}

	public void setFileBean(ECFileBean fileBean) {
		this.fileBean = fileBean;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	/**
	 * Sets the HTTP request object in implementing classes.
	 *
	 * @param request the HTTP request.
	 */
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		try {
			this.request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets the HTTP response object in implementing classes.
	 *
	 * @param response the HTTP response.
	 */
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		this.response.setCharacterEncoding("UTF-8");
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "fileUploadAction{" +
				"fileBean=" + fileBean +
				", creatorId=" + creatorId +
				", savePath='" + savePath + '\'' +
				", fileId=" + fileId +
				", projectId=" + projectId +
				", teamId=" + teamId +
				", file=" + file +
				", fileFileName='" + fileFileName + '\'' +
				", fileContentType='" + fileContentType + '\'' +
				'}';
	}
}
