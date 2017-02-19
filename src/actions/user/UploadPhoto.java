package actions.user;

import DAO.userDAO.UserDAO;
import DAO.userDAO.UserDAOImpl;
import bean.BusinessBean.file.FileIOBean;
import bean.domain.UserBean;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.Map;

/**
 * 头像上传的action
 * 手机端返回图片的地址
 * jsp 的 form里，name必须是file
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

	private String tempSavePath = "web/upload/headPhotos";

	public String uploadPic() throws Exception {
		UserBean userBean = (UserBean) session.get("userBean");
		setTempSavePath( ServletActionContext.getServletContext().getRealPath("")
				+ getTempSavePath() + "/" + userBean.getId() );
//		setSavePath( ServletActionContext.getServletContext().getRealPath("")+
//				"../../.." + getSavePath() + "/" + userBean.getId());
		//上传文件的逻辑代码
//		FileIOBean fileIOBean = new FileIOBean();
		try {
			int a = getFileFileName().lastIndexOf(".");
			//改变文件名为用户id
			String fileName = userBean.getId() + ".png";
			System.out.println("savePath = " + savePath);
			System.out.println("tempSavePath = " + tempSavePath);

			uploadFile( getTempSavePath(), fileName, getFile());
			System.out.println(2);
			uploadFile( getSavePath(), fileName, getFile());
			userBean.setPhoto(tempSavePath+fileName);
			session.remove("userBean");
			session.put("userBean", userBean);
			UserDAO userDAO = new UserDAOImpl();
			userDAO.updateInfo(userBean);

			System.out.println(1);
			return "success";
		}catch (Exception e){
			e.printStackTrace();
			return "fail";
		}
	}


	public void appUploadPic() throws Exception{
		JSONObject jsonObject = new JSONObject();
		System.out.println("appUploadPic");
		try {
			String path = appEditInfo();
			UserBean userBean = (UserBean) session.get("userBean");
			userBean.setPhoto(path);
			session.remove("userBean");
			session.put("userBean", userBean);
			jsonObject.put("result", "success");
			jsonObject.put("photoPath", userBean.getPhoto());
			jsonObject.put("userBean", userBean);
			UserDAO userDAO = new UserDAOImpl();
			userDAO.updateInfo(userBean);
			this.response.getWriter().write(jsonObject.toString());
			this.response.getWriter().flush();
			this.response.getWriter().close();
//			return "success";
		}catch (Exception e){
			jsonObject.put("result", "fail");
			this.response.getWriter().write(jsonObject.toString());
			this.response.getWriter().flush();
			this.response.getWriter().close();
			e.printStackTrace();
//			return "fail";
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

	public String getTempSavePath() {
		return tempSavePath;
	}

	public void setTempSavePath(String tempSavePath) {
		this.tempSavePath = tempSavePath;
	}

	public String uploadFile(String savePath, String fileName, File tempFile) throws Exception {

		try {
			File tempSavePath = new File(savePath);
			if (!tempSavePath.exists())
				tempSavePath.mkdirs();

			OutputStream os = new FileOutputStream(new File(savePath, fileName));
			InputStream is = new FileInputStream(tempFile);


			byte[] buffer = new byte[500];
			int length = 0;

			while (-1 != (length = is.read(buffer, 0, buffer.length))) {
				os.write(buffer);
			}

			os.close();
			is.close();

			return "success";
		}catch (Exception e){
			e.printStackTrace();
			throw e;

		}
	}
	public String appEditInfo( ) throws Exception{
		InputStream in = request.getInputStream();
		//里面填写你的工程目录下的WebContent
		UserBean userBean = (UserBean)session.get("userBean");
		String path = request.getServletContext().getRealPath("") + "upload/headphotos/" + userBean.getId() ;
		File tempFile = new File(path);
		if (!tempFile.exists()){
			tempFile.mkdirs();

		}
		String path1 = path +"/"+userBean.getId()+".png";

		File file1 = new File(path1);
		file1.createNewFile();
		path = path ;
//		tempFile = new File(path);
		FileOutputStream fos = new FileOutputStream(path+"/"+userBean.getId()+".png");
//		FileOutputStream fos2 = new FileOutputStream((ServletActionContext.getServletContext().getRealPath("")+
//				"../../.." + userBean.getId() +"/"+userBean.getId()+".png"));
		int len = 0;
		System.out.println("request"+request);
		System.out.println("path" + path);

		byte[] bytes = new byte[request.getContentLength()];
		while((len = in.read(bytes))!=-1){
			fos.write(bytes, 0, len);
			fos.flush();
		}
		fos.close();
		File source = new File(path1);
		String realPath = ServletActionContext.getServletContext().getRealPath("")+
				"../../../web/upload/headphotos/" + userBean.getId() ;

		File dest = new File(realPath);


		if (!dest.exists()){
			dest.mkdirs();
		}
		realPath += "/"+userBean.getId() + ".png";

		dest = new File(realPath);
		if (!dest.exists())
			dest.createNewFile();
		FileInputStream fi = null;
		FileOutputStream fo = null;
		FileChannel fin = null;
		FileChannel fout = null;

		try {
			System.out.println("source是"  + source);
			System.out.println("dest是" + dest);
			fi = new FileInputStream(source);
			fo = new FileOutputStream(dest);

			System.out.println("fi是" + fi);

			System.out.println("fo是" + fo);
			fin = fi.getChannel();//得到对应的文件通道
			fout = fo.getChannel();//得到对应的文件通道
			System.out.println("fin是" + fin);
			System.out.println("fout是" + fout);
			fin.transferTo(0, fin.size(), fout);//连接两个通道，并且从in通道读取，然后写入out通道
		}catch (IOException e){
			e.printStackTrace();
		}
//		dest = new File(realPath);
//		if (dest.exists()){
//			dest.delete();
//		}
//		Files.copy(source.toPath(), dest.toPath() );
		path += "/"+userBean.getId()+".png";
		return "upload/headphotos/" + userBean.getId() + "/" + userBean.getId() + ".png";
	}
}
