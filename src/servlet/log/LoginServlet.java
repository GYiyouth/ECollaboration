package servlet.log;

import DAO.teacherDAO.TeacherDAO;
import DAO.teacherDAO.TeacherDAOImpl;
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
 * Created by geyao on 2016/11/10.
 */
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String loginSuccessJsp = "log/teacher.jsp";
		String loginFailJsp = "login.jsp";


		request.setCharacterEncoding("utf-8");
		String logName = request.getParameter("logName");
		String passWord = request.getParameter("passWord");

		UserDAOImpl userdaoimpl = new UserDAOImpl();
		UserBean user = new UserBean();
		try {
			System.out.println("logName"+logName+"aaa");
			System.out.println("passWord"+passWord+"aaa");
			user = userdaoimpl.getLogerInfo(logName,passWord);
			//用户存在
			if(user!=null){

				HttpSession session = request.getSession();
				session.setAttribute("userId", user.getId());
				session.setAttribute("schoolId", user.getSchoolId());
				session.setAttribute("name", user.getName());
				session.setAttribute("sex", user.getSex());
				session.setAttribute("character", user.getCharacter());
				session.setAttribute("email", user.getEmail());
				session.setAttribute("phoneNumber", user.getPhoneNumber());
				session.setAttribute("logName", user.getLogName());
				session.setAttribute("passWord", user.getPassWord());
				session.setAttribute("createDate", user.getCreateDate());
				session.setAttribute("photo", user.getPhoto());
				session.setAttribute("lastLogTime", user.getLastLogTime());
				session.setAttribute("activeBefore", user.getActiveBefore());
				session.setAttribute("newFlag", user.getNewFlag());

				TeacherDAO teacherDAO = new TeacherDAOImpl();
				TeacherBean teacherBean = teacherDAO.getInfoById(user.getId());

				session.setAttribute("homePageUrl", teacherBean.getHomePageUrl());

				System.out.println(user);

				request.setAttribute("name", user.getName());
				request.setAttribute("homePageUrl", teacherBean.getHomePageUrl());
				request.setAttribute("schoolId", user.getSchoolId() );

				request.setAttribute("flag", 0 );

				request.getRequestDispatcher("station1.jsp").forward(request, response);

				System.out.println("有人"+user.getLogName());
				System.out.println("有人2"+user.getPassWord());
			}else{
			/*
			out.print("<script language='javascript'>" +
					"alert('修改成功');" +
					"window.location.href='" + loginFailJsp + "';</script>");
			*/
				System.out.println("没人");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
