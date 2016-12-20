package bean.BusinessBean.Code;

/**
 * Created by geyao on 2016/12/20.
 */
public class CommitBean {
	private int blank = 0;
	private int comment = 0;
	private int code = 0;

	public int getBlank() {
		return blank;
	}

	public void setBlank(int blank) {
		this.blank = blank;
	}

	public int getComment() {
		return comment;
	}

	public void setComment(int comment) {
		this.comment = comment;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "CommitBean{" +
				"blank=" + blank +
				", comment=" + comment +
				", code=" + code +
				'}';
	}
}
