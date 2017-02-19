package bean.domain;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 团队bean
 * Created by geyao on 2016/11/7.
 */
@Entity
@Table(name = "team")
public class TeamBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id = null;
	private String teamName = null;
    @ManyToOne(targetEntity = StudentBean.class)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "creatorId", referencedColumnName = "id")
	private StudentBean creatorStudentBean;
	private String createDate = null;
	private Integer memberMax;
	private String description;

	public Integer getMemberMax() {
		return memberMax;
	}

	public void setMemberMax(int memberMax) {
		this.memberMax = memberMax;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TeamBean() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

    public StudentBean getCreatorStudentBean() {
        return creatorStudentBean;
    }

    public void setCreatorStudentBean(StudentBean creatorStudentBean) {
        this.creatorStudentBean = creatorStudentBean;
    }

    public void setMemberMax(Integer memberMax) {
        this.memberMax = memberMax;
    }

    public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

    @Override
    public String toString() {
        return "TeamBean{" +
                "id=" + id +
                ", teamName='" + teamName + '\'' +
                ", creatorStudentBean=" + creatorStudentBean +
                ", createDate='" + createDate + '\'' +
                ", memberMax=" + memberMax +
                ", description='" + description + '\'' +
                '}';
    }
}
