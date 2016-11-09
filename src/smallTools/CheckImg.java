package smallTools;

import java.io.File;

/**
 * Created by geyao on 2016/11/9.
 */
public interface CheckImg {
	/**
	 * 检查一个普通文件是否是图片
	 * @param file
	 * @return
	 */
	public boolean isImg(File file);

	/**
	 * 判断图片大小是否小于给定值
	 * @param file
	 * @param width
	 * @param height
	 * @return
	 */
	public boolean isSmallThan(File file, int width, int height);

	/**
	 * 判断图片大小是否大于给定值
	 * @param file
	 * @param width
	 * @param height
	 * @return
	 */
	public boolean isBigThan(File file, int width, int height);

	/**
	 * 获取图片宽度
	 * @param file
	 * @return
	 */
	public int getImgWidth( File file);

	/**
	 * 获取图片高度
	 * @param file
	 * @return
	 */
	public int getImgHeight(File file);
}
