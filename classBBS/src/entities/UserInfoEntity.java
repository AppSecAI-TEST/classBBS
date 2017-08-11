package entities;

import javax.persistence.*;

/**
 * Created by huwendi on 2017/5/23.
 */
@Entity
@Table(name = "userInfo", schema = "bbs", catalog = "")
public class UserInfoEntity {
    private String userId;
    private String userClass;
    private String userName;
    private String userPhone;
    private String userEmail;

    @Id
    @Column(name = "userID", nullable = false, length = 11)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "userClass", nullable = false, length = 11)
    public String getUserClass() {
        return userClass;
    }

    public void setUserClass(String userClass) {
        this.userClass = userClass;
    }

    @Basic
    @Column(name = "userName", nullable = false, length = 11)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "userPhone", nullable = false, length = 11)
    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Basic
    @Column(name = "userEmail", nullable = false, length = 11)
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfoEntity that = (UserInfoEntity) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (userClass != null ? !userClass.equals(that.userClass) : that.userClass != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (userPhone != null ? !userPhone.equals(that.userPhone) : that.userPhone != null) return false;
        if (userEmail != null ? !userEmail.equals(that.userEmail) : that.userEmail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (userClass != null ? userClass.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userPhone != null ? userPhone.hashCode() : 0);
        result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
        return result;
    }
    @Override
    public String toString() {
        return "UserInfo userID: " + userId +
                " userClass: " + userClass +
                " userName: " + userName +
                " userPhone: " + userPhone +
                " userEmail: " + userEmail;
    }
}
