package smallTools;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用来处理和json相关的东西
 * Created by geyao on 2017/2/13.
 */
public class JSONHandler {
	public static void sendJSON(JSONObject jsonObject, HttpServletResponse response) throws IOException {
		if (!jsonObject.containsKey("result")){
			jsonObject.put("result", "fail");
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonObject.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}
}
