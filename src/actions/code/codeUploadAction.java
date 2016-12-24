package actions.code;

import DAO.codeDAO.CodeDAO;
import DAO.codeDAO.CodeDAOImpl;
import bean.BusinessBean.Code.CodeBusinessBean;
import bean.BusinessBean.Code.CommitBean;
import bean.BusinessBean.File.FileIOBean;
import bean.domain.CodeBean;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import smallTools.Time;
import smallTools.TimeImpl;

import java.io.File;
import java.util.Map;

/**
 * 上传代码，需要登录才能进行的操作
 * 此时session里必须要要有"userId" "projectId" "teamId"
 * 前端form里，指定 uploadCode()方法，name指定为"file"
 *
 * 此action完成后，将会在数据库里添加这个记录，也会在文件系统下放置这个代码文件
 * 如果有重名，自动覆盖
 * IO函数为FileIOBean里的reviewCode方法
 * 代码审核用的cloc
 * Created by geyao on 2016/12/24.
 */
public class codeUploadAction implements SessionAware{
	private CodeBean codeBean = new CodeBean();
	private CodeBusinessBean codeBusinessBean = new CodeBusinessBean();

	private String savePath;
	private CommitBean commitBean = new CommitBean();
	private Map session;

	//注意，file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
	private File file;

	//提交过来的file的名字
	private String fileFileName;

	//提交过来的file的MIME类型
	private String fileContentType;

	public String uploadCode(){
		CodeBeanInit();
		FileIOBean codeIOBean = new FileIOBean();

		//依然用FileIOBean完成上传工作。
		try {
			codeIOBean.uploadFile(getSavePath(), getFileFileName(), getFile());
			setCommitBean(codeBusinessBean.reviewCode(getCodeBean()));
			getCodeBean().setRow(getCommitBean().getCode());
			CodeDAO codeDAO = new CodeDAOImpl();
			codeDAO.add(codeBean);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	/**
	 * 初始化一个CodeBean，除了行数，别的都填了
	 * 代码的路径放在web/upload/projectId/teamId/studentId下
	 */
	public void CodeBeanInit(){
		Time time = new TimeImpl();
		getCodeBean().setCreateDate(time.getTime());
		getCodeBean().setDeadDate(time.getDeadTime());
		getCodeBean().setDownLoadTimes(0);
		getCodeBean().setStudentId((int)session.get("userId"));
		getCodeBean().setProjectId((int)session.get("projectId"));
		getCodeBean().setTeamId((int)session.get("teamId"));
		setSavePath( ServletActionContext.getServletContext().getRealPath("")+
				"/../../../web/upload/" + getCodeBean().getProjectId() +
				"/" + getCodeBean().getProjectId() +
				"/" + getCodeBean().getStudentId());
		getCodeBean().setPath(getSavePath());
		getCodeBean().setCodeName( getFileFileName() );
	}





	public CodeBean getCodeBean() {
		return codeBean;
	}

	public void setCodeBean(CodeBean codeBean) {
		this.codeBean = codeBean;
	}

	public CodeBusinessBean getCodeBusinessBean() {
		return codeBusinessBean;
	}

	public void setCodeBusinessBean(CodeBusinessBean codeBusinessBean) {
		this.codeBusinessBean = codeBusinessBean;
	}


	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
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

	public CommitBean getCommitBean() {
		return commitBean;
	}

	public void setCommitBean(CommitBean commitBean) {
		this.commitBean = commitBean;
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
}
