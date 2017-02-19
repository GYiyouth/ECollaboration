package bean.domain;

public class UserBean {
    private Integer id;
    private String schoolId;
    private String name;
    private Integer sex;	//1:男 0：女
    private Integer role;//1管理员，2教师，3学生
    private String email;
    private String phoneNumber;
    private String logName;
    private String passWord;
    private String createDate;
    private String photo; //路径，默认放在web.upload.headPhotos下
    private String lastLogTime;
    private String activeBefore;
    private int newFlag;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getSchoolId() {
        return schoolId;
    }
    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getSex() {
        return sex;
    }
    public void setSex(Integer sex) {
        this.sex = sex;
    }
    public Integer getRole() {
        return role;
    }
    public void setRole(Integer role) {
        this.role = role;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getLogName() {
        return logName;
    }
    public void setLogName(String logName) {
        this.logName = logName;
    }
    public String getPassWord() {
        return passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String getLastLogTime() {
        return lastLogTime;
    }
    public void setLastLogTime(String lastLogTime) {
        this.lastLogTime = lastLogTime;
    }
    public String getActiveBefore() {
        return activeBefore;
    }
    public void setActiveBefore(String activeBefore) {
        this.activeBefore = activeBefore;
    }
    public int getNewFlag() {
        return newFlag;
    }
    public void setNewFlag(int newFlag) {
        this.newFlag = newFlag;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", schoolId='" + schoolId + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", character=" + role +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", logName='" + logName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", createDate='" + createDate + '\'' +
                ", photo='" + photo + '\'' +
                ", lastLogTime='" + lastLogTime + '\'' +
                ", activeBefore='" + activeBefore + '\'' +
                ", newFlag=" + newFlag +
                '}';
    }
}
