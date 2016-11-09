package smallTools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by geyao on 2016/11/9.
 */
public class CheckImgImpl implements CheckImg {
	/**
	 * 检查一个普通文件是否是图片
	 *
	 * @param file
	 * @return
	 */
	@Override
	public boolean isImg(File file) {
		if (!file.exists())
			return false;
		Image image = null;
		try {
			image = ImageIO.read(file);
			if (image == null || image.getWidth(null) <= 0 || image.getHeight(null) <= 0)
				return false;
			return true;
		}catch (IOException e){
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 判断图片大小是否小于给定值
	 *
	 * @param file
	 * @param width
	 * @param height
	 * @return
	 */
	@Override
	public boolean isSmallThan(File file, int width, int height) {
		if (!isImg(file))
			return false;
		try {
			Image image = ImageIO.read(file);
			if (image != null && image.getWidth(null) <= width && image.getHeight(null) <= height)
				return true;
			return false;
		}catch (IOException e){
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 判断图片大小是否大于给定值
	 *
	 * @param file
	 * @param width
	 * @param height
	 * @return
	 */
	@Override
	public boolean isBigThan(File file, int width, int height) {
		if (!isImg(file))
			return false;
		try {
			Image image = ImageIO.read(file);
			if (image != null && image.getWidth(null) >= width && image.getHeight(null) >= height)
				return true;
			return false;
		}catch (IOException e){
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 获取图片宽度
	 *
	 * @param file
	 * @return
	 */
	@Override
	public int getImgWidth(File file) {
		return 0;
	}

	/**
	 * 获取图片高度
	 *
	 * @param file
	 * @return
	 */
	@Override
	public int getImgHeight(File file) {
		return 0;
	}
}
