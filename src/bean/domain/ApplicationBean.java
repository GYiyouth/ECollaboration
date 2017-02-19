package bean.domain;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by geyao on 2017/2/18.
 */
@Entity
@Table(name = "application")
public class ApplicationBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;
    @ManyToOne(targetEntity = TeamBean.class)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "teamId", referencedColumnName = "id")
    private TeamBean teamBean;
    @ManyToOne(targetEntity = ProjectBean.class)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "projectId", referencedColumnName = "id")
    private ProjectBean projectBean;
    @ManyToOne(targetEntity = UserBean.class)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "handlerId", referencedColumnName = "id")
    private UserBean handlerUserBean;
    @ManyToOne(targetEntity = UserBean.class)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "affectedId", referencedColumnName = "id")
    private UserBean affectedUserBean;
    private String createdTime;
    private String handledTime;
    private String result;

    public ApplicationBean() {
    }

    @Override
    public String toString() {
        return "ApplicationBean{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", teamBean=" + teamBean +
                ", projectBean=" + projectBean +
                ", handlerUserBean=" + handlerUserBean +
                ", affectedUserBean=" + affectedUserBean +
                ", createdTime='" + createdTime + '\'' +
                ", handledTime='" + handledTime + '\'' +
                ", result='" + result + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TeamBean getTeamBean() {
        return teamBean;
    }

    public void setTeamBean(TeamBean teamBean) {
        this.teamBean = teamBean;
    }

    public ProjectBean getProjectBean() {
        return projectBean;
    }

    public void setProjectBean(ProjectBean projectBean) {
        this.projectBean = projectBean;
    }

    public UserBean getHandlerUserBean() {
        return handlerUserBean;
    }

    public void setHandlerUserBean(UserBean handlerUserBean) {
        this.handlerUserBean = handlerUserBean;
    }

    public UserBean getAffectedUserBean() {
        return affectedUserBean;
    }

    public void setAffectedUserBean(UserBean affectedUserBean) {
        this.affectedUserBean = affectedUserBean;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getHandledTime() {
        return handledTime;
    }

    public void setHandledTime(String handledTime) {
        this.handledTime = handledTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
