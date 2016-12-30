package bean.BusinessBean.file;

import java.io.*;

/**
 * Created by geyao on 2016/12/23.
 */
public class FileIOBean {

	public FileIOBean() {
		super();
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
}
