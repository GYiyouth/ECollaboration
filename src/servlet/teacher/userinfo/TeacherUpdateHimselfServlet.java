package servlet.teacher.userinfo;

import DAO.teacherDAO.TeacherDAO;
import DAO.teacherDAO.TeacherDAOImpl;
import DAO.userDAO.UserDAO;
import DAO.userDAO.UserDAOImpl;
import bean.domain.TeacherBean;
import bean.domain.UserBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by geyao on 2016/11/9.
 */
public class TeacherUpdateHimselfServlet extends HttpServlet {
	private String teacherCenterJsp = "";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		Integer id = Integer.parseInt(session.getAttribute("id").toString());
		String phone = session.getAttribute("phoneNumber").toString();
		String email = session.getAttribute("email").toString();

		PrintWriter out   =   response.getWriter();

		String homePageUrl = request.getParameter("homePageUrl");
		TeacherDAO teacherDAO = new TeacherDAOImpl();
		try {
			//现修改主页
			TeacherBean teacherBean = teacherDAO.getInfoById(id);
			teacherBean.setHomePageUrl(homePageUrl);
			teacherDAO.updateInfo(teacherBean);
			//现在修改user表
			UserDAO userDAO = new UserDAOImpl();
			UserBean userBean = userDAO.getUserInfoById(id);
			userBean.setPhoneNumber(phone);
			userBean.setEmail(email);
			out.print("<script language='javascript'>" +
					"alert('修改成功');" +
					"window.location.href='" + teacherCenterJsp + "';</script>");
		} catch (SQLException e) {
			out.print("<script language='javascript'>" +
					"alert('发生错误');" +
					"window.location.href='" + teacherCenterJsp + "';</script>");
			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
