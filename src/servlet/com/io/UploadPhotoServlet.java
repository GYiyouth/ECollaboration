package servlet.com.io;

import DAO.ECFileDAO.ECFileDAO;
import DAO.ECFileDAO.ECFileDAOImpl;
import DAO.userDAO.UserDAO;
import DAO.userDAO.UserDAOImpl;
import bean.domain.ECFileBean;
import bean.domain.UserBean;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import smallTools.CheckImg;
import smallTools.CheckImgImpl;
import smallTools.Time;
import smallTools.TimeImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.SQLException;
import java.util.List;



/**
 * Created by geyao on 2016/11/9.
 */
public class UploadPhotoServlet extends HttpServlet {
	//用户中心jsp
	private String userCenterJsp = "";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//上传文件的保存目录
		String savePath = this.getServletContext().getRealPath("/WEB-INF/upload/user/photo");
		File file = new File(savePath);
		//判断上传文件的保存目录是否存在
		if (!file.exists() && !file.isDirectory()) {
			System.out.println(savePath+"目录不存在, 需要创建");
			//创建目录
			file.mkdir();
		}
		String message = "";
		try {
			//使用Apache文件上传组件处理文件上传步骤：
			//1、创建一个DiskFileItemFactory工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//2、创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			//解决上传文件名的中文乱码
			upload.setHeaderEncoding("UTF-8");
			//3、判断提交上来的数据是否是上传文件的数据
			PrintWriter out   =   response.getWriter();
			if(!ServletFileUpload.isMultipartContent(request)){
				//按照传统方式获取数据
				out.print("<script language='javascript'>" +
						"alert('发生错误');" +
						"window.location.href='" +userCenterJsp+ "';</script>");
				return;
			}
			//4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
			List<FileItem> list = upload.parseRequest(request);
			for(FileItem item : list){
				//如果fileitem中封装的是普通输入项的数据
				if(item.isFormField()){
					String name = item.getFieldName();
					//解决普通输入项的数据的中文乱码问题
					String value = item.getString("UTF-8");
					//value = new String(value.getBytes("iso8859-1"),"UTF-8");
					System.out.println(name + "=" + value);
				}
				else {
					//判断文件性质、大小
					CheckImg checkImg = new CheckImgImpl();
					if (!checkImg.isImg((File)item)){
						//如果不是图片

						out.print("<script language='javascript'>" +
								"alert('文件类型错误');" +
								"window.location.href='" +userCenterJsp+ "';</script>");
						return;
					}
					if (checkImg.isBigThan((File)item, 1000, 1000)){
						out.print("<script language='javascript'>" +
								"alert('文件过大，请勿超过1000*1000');" +
								"window.location.href='" +userCenterJsp+ "';</script>");
						return;
					}
					if (checkImg.isSmallThan((File)item, 50, 50)){
						out.print("<script language='javascript'>" +
								"alert('文件过小，请勿小于50 * 50');" +
								"window.location.href='" +userCenterJsp+ "';</script>");
						return;
					}


					//如果fileitem中封装的是上传文件
					//得到上传的文件名称

					String filename = item.getName();
					System.out.println(filename);
					System.out.println(savePath);
					if(filename==null || filename.trim().equals("")){
						continue;
					}
					//注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
					//处理获取到的上传文件的文件名的路径部分，只保留文件名部分
					filename = filename.substring(filename.lastIndexOf("/")+1);
					//获取item中的上传文件的输入流
					InputStream in = item.getInputStream();
					//创建一个文件输出流
					FileOutputStream fileOutputStream = new FileOutputStream(savePath + "/" + filename);
					//创建一个缓冲区
					byte buffer[] = new byte[1024];
					//判断输入流中的数据是否已经读完的标识
					int len = 0;
					//循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
					while((len=in.read(buffer))>0){
						//使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
						fileOutputStream.write(buffer, 0, len);
					}
					try {


						ECFileBean ecFileBean = new ECFileBean();
						HttpSession session = request.getSession();

						int creatorId = Integer.parseInt(session.getAttribute("userId").toString());


						Time time = new TimeImpl();
						ecFileBean.setCreateDate(time.getDateStr());

						ecFileBean.setCreatorId(creatorId);
						ecFileBean.setFileName(filename);
						ecFileBean.setPath(savePath);
						ecFileBean.setPriority(4);
						ecFileBean.setDownLoadTimes(0);

						//添加新头像，删除旧头像
						ECFileDAO ecFileDAO = new ECFileDAOImpl();
//						int oldFileId = ecFileDAO.getPhotoId(creatorId);
//						ecFileDAO.addPhoto(ecFileBean, creatorId);
//						ecFileDAO.deleteFile(oldFileId);
						//修改user里photo信息
						UserDAO userDAO = new UserDAOImpl();
						UserBean userBean = userDAO.getUserInfoById(creatorId);
						String photo = savePath + filename;
						userBean.setPhoto(photo);

						request.setAttribute("photo", photo);

					}catch (SQLException e){
						e.printStackTrace();
						out.print("<script language='javascript'>" +
								"alert('文件已上传，但数据库操纵失败');" +
								"window.location.href='Login.jsp';</script>");
					}catch (NullPointerException e){
						e.printStackTrace();
						out.print("<script language='javascript'>" +
								"alert('文件已上传，但数据库操纵失败，');" +
								"window.location.href='Login.jsp';</script>");
					}finally {
						out.close();
					}


					//关闭输入流
					in.close();
					//关闭输出流
					fileOutputStream.close();
					//删除处理文件上传时生成的临时文件
					item.delete();
					message = "文件上传成功！";


				}
			}
		}catch (Exception e){
			message = "文件上传失败";
			e.printStackTrace();
		}
		request.setAttribute("message",message);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
