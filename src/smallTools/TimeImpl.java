package smallTools;

import java.util.Date;

/**
 * Created by geyao on 2016/10/29.
 */
public class TimeImpl implements Time{


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
		return (time.getYear()+time.getMonth()+ time.getDay());

	}

	@Override
	public Date getDate() {
		return new Date();
	}

	@Override
	public String getDeadTime() {
		String year = getYear();
		int deadYear = Integer.parseInt(year) + 10;
		return (deadYear + "-" + getMonth() + "-" + getDay() + " " +  getHour() + ":" + getMinute() + ":" + getSecond());
	}

	@Override
	public String getSecond() {
		Date date = new Date();
		String second = String.format("%tS", date);
		return second;
	}

	@Override
	public String getTime() {
//		Date date = new Date();
//		String time = String.format("%tY-%tm-%td %tH:%tM:%tS", date,date,date,date,date,date);
//		return time;
		return (getYear() + "-" + getMonth() + "-" + getDay() + " " +  getHour() + ":" + getMinute() + ":" + getSecond());
	}
}
