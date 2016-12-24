package actions.file;

import DAO.ECFileDAO.ECFileDAO;
import DAO.ECFileDAO.ECFileDAOImpl;
import DAO.studentDAO.StudentDAO;
import DAO.studentDAO.StudentDaoImpl;
import bean.BusinessBean.File.FileIOBean;
import bean.domain.ECFileBean;
import bean.domain.UserBean;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import smallTools.Time;
import smallTools.TimeImpl;

import java.io.File;
import java.util.Map;

/**
 * 上传文件的action
 * 要求session里有userId，userBean等东西，已经加在了登录的action里
 *
 * Created by geyao on 2016/12/24.
 */
public class fileUploadAction implements SessionAware {
	private Map session;
	private ECFileBean fileBean = new ECFileBean();
	private Integer creatorId;
	private String savePath;
	private Integer fileId;

	//注意，file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
	private File file;

	//提交过来的file的名字
	private String fileFileName;

	//提交过来的file的MIME类型
	private String fileContentType;

	//仅用作学生上传学校工程实践项目的文件
	public String studentUploadFile(){
		if (session.containsKey("projectId") && session.containsKey("teamId")){
			try {
			int projectId = (int)session.get("projectId");
			int teamId = (int)session.get("teamId");

			fileBeanInit("/schoolPractices/" + projectId + "/" +teamId);
			FileIOBean fileIOBean = new FileIOBean();

				fileIOBean.uploadFile( getSavePath(), getFileFileName(), getFile());
				ECFileDAO fileDAO = new ECFileDAOImpl();
				int id = fileDAO.addFile( getFileBean() );
				setFileId(id);
				getFileBean().setId(id);
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

	public void fileBeanInit(String path){
		getFileBean().setFileName(getFileFileName());
		Time time = new TimeImpl();
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
}
