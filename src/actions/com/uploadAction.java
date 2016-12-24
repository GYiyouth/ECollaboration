package actions.com;

import bean.BusinessBean.File.FileIOBean;
import org.apache.struts2.ServletActionContext;

import java.io.*;

/**
 * 这里的一些新发现。
 * ServletActionContext.getServletContext().getRealPath("") =
 * /Users/geyao/IdeaProjects/ECollaborationGit/out/artifacts/ECollaborationWeb
 * 如果在后面再人为加上"/../../.."
 * 就可以回到根目录下，和src,web一个目录。
 *
 * 另外，jsp里，文件那一栏对应的name必须是file
 *
 * 仅做测试，实际上用不到的
 * Created by geyao on 2016/12/23.
 */
public class uploadAction {


	//注意，file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
	private File file;

	//提交过来的file的名字
	private String fileFileName;

	//提交过来的file的MIME类型
	private String fileContentType;

	//想让文件存储在哪里，就直接写在这里就好了，如果为空，则会和操作日志放在一个文件夹下。
	private String savePath = "/web/upload/headPhotos";

	public String uploadPic() {
		setSavePath( ServletActionContext.getServletContext().getRealPath("")+"/../../.." + getSavePath() );
		//上传文件的逻辑代码
		FileIOBean fileIOBean = new FileIOBean();
		try {
			fileIOBean.uploadFile(getSavePath(), getFileFileName(), getFile());
			return "success";
		}catch (Exception e){
			e.printStackTrace();
			return "fail";
		}
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
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
