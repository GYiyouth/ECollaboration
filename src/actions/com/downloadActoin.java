package actions.com;

import org.apache.struts2.ServletActionContext;

import java.io.InputStream;

/**
 * 下载的资料在web目录下的都可以
 * Created by geyao on 2016/12/23.
 */
public class downloadActoin {
	private String fileName;//要下载的文件名
	private InputStream inputStream;

	public String downloadFiles() throws Exception {
		inputStream = ServletActionContext.getServletContext().getResourceAsStream("/" + fileName);
		return "success";
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
}
