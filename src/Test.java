import DAO.ECFileDAO.ECFileDAO;
import DAO.ECFileDAO.ECFileDAOImpl;
import DAO.codeDAO.CodeDAO;
import DAO.codeDAO.CodeDAOImpl;
import DAO.com.smallTools.ComGetListValueDAO;
import DAO.com.smallTools.ComGetListValueDAOImpl;
import DAO.com.smallTools.ComGetSingleValueDAO;
import DAO.com.smallTools.ComGetSingleValueDAOImpl;
import DAO.projectDAO.ProjectDAO;
import DAO.projectDAO.ProjectDAOImpl;
import DAO.teamDAO.TeamDAO;
import DAO.teamDAO.TeamDAOImpl;
import DAO.userDAO.UserDAOImpl;
import bean.domain.ProjectBean;
import bean.domain.TeamBean;
import bean.domain.UserBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.sql.SQLException;

/**
 * Created by geyao on 2016/11/9.
 */
public class Test {
	public static  void  main(String[] args) {
//		String a = "123";
//		String b = a ;
//		System.out.println(a==b);
//		a = a + "45";
//		System.out.println(a);
//		System.out.println(b);
//		System.out.println(a==b);
//
//		ECFileDAO ecFileDAO = new ECFileDAOImpl();
//		ECFileBean ecFileBean = null;
//		try {
//15:44test
//15:46test1
//15:50test葛尧
//发生的hahaha
//			ecFileBean = ecFileDAO.getFileInfo(2);
//			ecFileBean.setFileName("photo");
//			int id = ecFileDAO.addPhoto(ecFileBean, 2);
//			System.out.println(id);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		Time time = new TimeImpl();
//		System.out.println(time.getDateStr());
//		System.out.println(time.getDeadDateStr());
//		CodeBean codeBean = new CodeBean();
//		codeBean.setDownLoadTimes(0);
//		codeBean.setPath("lala");
//		codeBean.setProjectId(123);
//		codeBean.setStudentId(123);
//		codeBean.setScore(56);
//		codeBean.setRow(123);
//		codeBean.setTeamId(555);
//
//
//		CodeDAO codeDAO = new CodeDAOImpl();
//		try {
//			codeBean = codeDAO.deleteCode(2);
//			System.out.println(codeBean);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		UserDAO  userDAO = new UserDAOImpl();
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		ResultSet resultSet = null;
//		try {
//			UserBean userBean = userDAO.getUserInfoById(1);
//			String sql = "SELECT photo FROM ECollaborationWeb.user WHERE id = 1";
//			connection = DBUtils.getConnetction();
//
//			preparedStatement = connection.prepareStatement(sql);
//			resultSet = preparedStatement.executeQuery();
//			resultSet.next();
//			int id = userBean.getId();
//			File file = new File("/User/geyao/Desktop/" + id +".jpg");
//			if (file.exists()){
//				file.delete();
//			}
//			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("/Users/geyao/Desktop/" + id + ".jpg"));
//			byte[] buf=new byte[1024];
//			BufferedInputStream bufferedInputStream = new BufferedInputStream(resultSet.getBinaryStream(1));
//			int count = -1;
//			while ((count = bufferedInputStream.read(buf, 0, 1024)) != -1 ){
//				bufferedOutputStream.write(buf, 0, count);
//			}
//			bufferedOutputStream.flush();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//
//		}finally {
//			DBUtils.close(resultSet, preparedStatement, connection);
//		}
//
//		ECFileDAO ecFileDAO = new ECFileDAOImpl();
//		TeamDAO teamDAO = new TeamDAOImpl();
//		TeamBean teamBean = new TeamBean();
//		teamBean.setTeamName("青葱岁月");
//		teamBean.setCreatorId(13);
//		teamBean.setCreateDate("20161201");
//		teamBean.setId(1);

//			System.out.println(ecFileDAO.getFileIdListByProjectId(1));
//			System.out.println(ecFileDAO.getFileIdListByTeacherId(1));
//			System.out.println(ecFileDAO.getFileIdListByTeamId(1));
//			System.out.println(ecFileDAO.getFileIdListByCreatorIdProjectId(1, 1));
//			System.out.println(ecFileDAO.getFileIdListByTeacherIdProjectId(1, 1));
//			System.out.println(ecFileDAO.getFileIdLIstByTeamIdProjectId(1, 1));
//			int i = teamDAO.addTeam(teamBean);
//			teamBean.setId(i);
//			System.out.println(i);
//			System.out.println(teamDAO.deleteTeam(teamBean.getId()));
//			System.out.println(fileList);
//			System.out.println(id);
//			ComGetSingleValueDAO<String, Integer> comGetSingleValueDAO = new ComGetSingleValueDAOImpl<>();
//			String str = comGetSingleValueDAO.getAbyIntBfromC("teamName", "id", 1, "team");
//			System.out.println(str);
//			ComGetListValueDAO<String, Integer> comGetListValueDAO = new ComGetListValueDAOImpl<>();
//			System.out.println(comGetListValueDAO.getListAfromBbyC("teamName", "id", 1, "team"));
//			System.out.println(comGetListValueDAO.getListAfromBbyC("teamName", "creatorId", 12, "team"));
//
//			System.out.println(teamDAO.getTeamIdListByProjectId(2));
//			CodeDAO codeDAO = new CodeDAOImpl();
//			System.out.println(teamDAO.getTeamIdListByStudentId(123));
//			System.out.println(teamDAO.getTeamIdListByStudentIdCodeId(123, 1));
//			System.out.println(teamDAO.getTeamIdListByStudentIdProjectId(1, 1));
//			System.out.println(teamDAO.getTeamIdListByCodeId(1));
//			System.out.println(teamDAO.getTeamIdListByTeacherId(1));
//			System.out.println(teamDAO.getTeamIdListByTeacherIdTaskId(1, 1));
//			System.out.println(teamDAO.getTeamIdListByTeacherIdProjectId(1, 1));
//			System.out.println(teamDAO.getTeamIdListByTeacherIdTaskIdProjectId(1, 1, 1));
//			System.out.println(codeDAO.getCodeIdListByStudentId(123));
//			System.out.println(codeDAO.getCodeIdListByProjectId(1));
//			System.out.println(codeDAO.getCodeIdListByTeamId(1));
//			System.out.println(codeDAO.getCodeIdListByStudentIdProjectId(123, 123));
//			System.out.println(codeDAO.getCodeIdListByStudentIdTeamId(123, 1));
//			System.out.println(codeDAO.getCodeIdListByProjectIdTeamId(1, 1));
//			System.out.println(codeDAO.getCodeIdListByProjectIdTeamIdStudentId(123, 555, 123));
//			System.out.println(teamDAO.getTeamIdListByTeacherId(1));
//			System.out.println(teamDAO.getTeamIdListByTeacherIdProjectId(1, 1));
//			ProjectBean projectBean = new ProjectBean();
//			projectBean.setName("123");
//			ProjectDAO projectDAO = new ProjectDAOImpl();
//			System.out.println(projectDAO.addProject(projectBean));
//			JSONArray jsonArray = new JSONArray();
//			JSONObject jsonObject = new JSONObject();
//			UserBean userBean = new UserDAOImpl().getUserInfoById(1);
//			userBean.setPhoto("我是photo路径");
//			jsonObject.put("userBean", userBean);
//			jsonArray.add(jsonObject);
//			System.out.println(jsonObject);
//			System.out.println(jsonArray);
			try {
				String[] cmd = new String[]{"cd ", "cd Desktop ", "ls"," cloc /Users/geyao/IdeaProjects/ECollaborationGit/src/actions/log/LogAction.java -xml -out=/Users/geyao/123.xml", "cd /Users/geyao/Desktop"};
//				Runtime.getRuntime().exec(cmd[0]);
//				Runtime.getRuntime().exec(cmd[1]);
				Process p = Runtime.getRuntime().exec(cmd[3]);

				InputStreamReader ir = new InputStreamReader(p.getInputStream());
				LineNumberReader input = new LineNumberReader(ir);      //创建IO管道，准备输出命令执行后的显示内容

				InputStreamReader er = new InputStreamReader(p.getErrorStream());
				LineNumberReader ErrorInput = new LineNumberReader(er);
				String line;
				while ((line = input.readLine ()) != null){     //按行打印输出内容
					System.out.println(line);
				}
				while ((line = ErrorInput.readLine()) != null){
					System.out.println(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

	}
}
