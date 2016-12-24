package actions.user;

import bean.BusinessBean.File.FileIOBean;
import bean.domain.UserBean;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 头像上传的action
 * Created by geyao on 2016/12/24.
 */
public class UploadPhoto implements SessionAware,ServletRequestAware, ServletResponseAware {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map session;
	//注意，file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
	private File file;

	//提交过来的file的名字
	private String fileFileName;

	//提交过来的file的MIME类型
	private String fileContentType;

	//想让文件存储在哪里，就直接写在这里就好了，如果为空，则会和操作日志放在一个文件夹下。
	private String savePath = "/web/upload/headPhotos";

	public String uploadPic() throws Exception {
		UserBean userBean = (UserBean) session.get("userBean");
		setSavePath( ServletActionContext.getServletContext().getRealPath("")+
				"/../../.." + getSavePath() + "/" + userBean.getId());
		//上传文件的逻辑代码
		FileIOBean fileIOBean = new FileIOBean();
		try {
			int a = getFileFileName().lastIndexOf(".");
			//改变文件名为用户id
			String fileName = userBean.getId() + getFileFileName().substring(a);
			fileIOBean.uploadFile(getSavePath(), fileName, getFile());
			return "success";
		}catch (Exception e){
			e.printStackTrace();
			return "fail";
		}
	}

	public void appUploadPic() throws Exception{
		JSONObject jsonObject = new JSONObject();
		if (uploadPic().equals("success")){
			jsonObject.put("result", "success");
			jsonObject.put("photoPath", getSavePath());
			this.response.getWriter().write(jsonObject.toString());
			this.response.getWriter().flush();
			this.response.getWriter().close();
		}else {
			jsonObject.put("result", "fail");
			this.response.getWriter().write(jsonObject.toString());
			this.response.getWriter().flush();
			this.response.getWriter().close();
		}
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

	/**
	 * Sets the Map of session attributes in the implementing class.
	 *
	 * @param session a Map of HTTP session attribute name/value pairs.
	 */
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
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
			this.request.setCharacterEncoding("UTF_8");
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
}
