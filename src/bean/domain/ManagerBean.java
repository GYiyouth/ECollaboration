package bean.domain;

public class ManagerBean {
    private Integer id;
    private Integer role;  //0：超级管理员 1：普通管理员

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
