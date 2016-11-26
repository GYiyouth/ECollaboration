package smallTools;

import java.util.Date;

/**
 * Created by geyao on 2016/11/1.
 */
public interface Time {
	public String getYear();
	public String getMonth();
	public String getDay();
	public String getHour();
	public String getMinute();
	public String getSecond();
	public String getDateStr();
	public Date getDate();
	public String getDeadDateStr();
}
