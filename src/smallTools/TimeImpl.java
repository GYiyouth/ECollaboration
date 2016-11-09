package smallTools;

import java.util.Date;

/**
 * Created by geyao on 2016/10/29.
 */
public class TimeImpl implements Time{

//	public static Calendar calendar = Calendar.getInstance();
//
//	public static int getYear(){    // 返回年份比如 2012
//		return calendar.get(Calendar.YEAR);
//	}
//	public static int getMonth(){   //返回月份，比如1，10，12
//		return calendar.get(Calendar.MONTH);
//	}
//	public static int getDay(){     //返回日期，比如29，1
//		return calendar.get(Calendar.DATE);
//	}
//	public static int getHour(){    //返回小时
//		return calendar.get(Calendar.HOUR);
//	}
//	public static int getMinute(){     //返回分钟
//		return calendar.get(Calendar.MINUTE);
//	}
//	public static int getSecond(){      //返回秒钟
//		return calendar.get(Calendar.SECOND);
//	}
	@Override
	public String getYear(){
		Date date = new Date();
		String year = String.format("%tY", date);
		return year;
	}

	@Override
	public String getMonth() {
		Date date = new Date();
		String month = String.format("%tm", date);
		return month;
	}

	@Override
	public String getDay() {
		Date date = new Date();
		String day = String.format("%td", date);
		return day;
	}

	@Override
	public String getHour() {
		Date date = new Date();
		String hour = String.format("%tH", date);
		return hour;
	}

	@Override
	public String getMinute() {
		Date date = new Date();
		String minute = String.format("%tM", date);
		return minute;
	}

	@Override
	public String getDateStr() {

		Time time = new TimeImpl();
		return time.getYear()+time.getMonth()+ time.getDay();

	}

	@Override
	public Date getDate() {
		return new Date();
	}

	@Override
	public String getSecond() {
		Date date = new Date();
		String second = String.format("%tS", date);
		return second;
	}
}
