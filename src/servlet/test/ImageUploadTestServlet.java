package servlet.test;

import DAO.userDAO.UserDAO;
import DAO.userDAO.UserDAOImpl;
import bean.domain.UserBean;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 详细参考 http://www.ahlinux.com/java/19808.html
 * Created by geyao on 2016/11/17.
 */
public class ImageUploadTestServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String savePath = "/Users/geyao/IdeaProjects/Web/ECollaboration/web/WEB-INF";
		System.out.println(this.getServletContext().getRealPath("/WEB-INF"));
		File file = new File(savePath);
		if ( !file.exists() && file.isDirectory()){
			//路径不存在
			System.out.println("路径不存在");
			file.mkdir();
		}
		System.out.println("savePath = " + savePath);
		String message = null;
		try {
			DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
			ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
			servletFileUpload.setHeaderEncoding("UTF-8");
			if (!ServletFileUpload.isMultipartContent(request)){
				//按照传统方式获取
				return;
			}
			List<FileItem> fileItemList = servletFileUpload.parseRequest(request);
			//如果item封装的是普通输入想数据
			for (FileItem item : fileItemList)
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
					System.out.println(name + " = " + value);
				} else {//上传的是文件
					String fileName = item.getName();
					System.out.println(fileName);
					if (fileName == null || fileName.trim().equals("")) {
						continue;
					}
					//注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的
					// 如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
					//处理获取到的上传文件的文件名的路径部分，只保留文件名部分
					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
					System.out.println(fileName);
					InputStream inputStream = item.getInputStream();
					FileOutputStream fileOutputStream = new FileOutputStream(savePath +"/" + fileName);
					byte buffer[] = new byte[1024];
					int len = 0;
					//循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
					while ((len = inputStream.read(buffer)) > 0){
						fileOutputStream.write(buffer, 0, len);
					}
					UserDAO userDAO = new UserDAOImpl();
					UserBean userBean = userDAO.getUserInfoById(1);
					File photo = new File(savePath + "/" +fileName);
					userDAO.setUserPhoto(userBean, photo);
					inputStream.close();
					fileOutputStream.close();
					item.delete();
					photo.delete();
					System.out.println(1);
				}
			message = "成功";
		}catch (Exception e){
			message = "失败";
			System.out.println(2);
			e.printStackTrace();
		}
		System.out.println(message);
		request.setAttribute("message", message);
		HttpSession session = request.getSession();
		session.setAttribute("message", message);
		request.getRequestDispatcher("MessageJumpJSP.jsp").forward(request, response);
		System.out.println(message);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
