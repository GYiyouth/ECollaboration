package bean.domain;

public class ManagerBean extends UserBean{
    private Integer id;
    private Integer mRole;  //0：超级管理员 1：普通管理员

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getmRole() {
        return mRole;
    }

    public void setmRole(Integer mRole) {
        this.mRole = mRole;
    }
}
