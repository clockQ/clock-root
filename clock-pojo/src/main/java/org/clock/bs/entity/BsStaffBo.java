package org.clock.bs.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by clock on 2017/4/13.
 */
@Entity
@Table(name = "bs_staff", schema = "clock_bs")
public class BsStaffBo  implements Serializable {
    private int staffId;
    private String email;
    private String password;
    private String name;
    private String userNumber;
    private Integer companyId;
    private String companyName;
    private String phone;
    private String leave;

    @Id
    @Column(name = "STAFF_ID")
    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "USER_NUMBER")
    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    @Basic
    @Column(name = "COMPANY_ID")
    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "COMPANY_NAME")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "LEAVE")
    public String getLeave() {
        return leave;
    }

    public void setLeave(String leave) {
        this.leave = leave;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BsStaffBo bsStaffBo = (BsStaffBo) o;

        if (staffId != bsStaffBo.staffId) return false;
        if (email != null ? !email.equals(bsStaffBo.email) : bsStaffBo.email != null) return false;
        if (password != null ? !password.equals(bsStaffBo.password) : bsStaffBo.password != null) return false;
        if (name != null ? !name.equals(bsStaffBo.name) : bsStaffBo.name != null) return false;
        if (userNumber != null ? !userNumber.equals(bsStaffBo.userNumber) : bsStaffBo.userNumber != null) return false;
        if (companyId != null ? !companyId.equals(bsStaffBo.companyId) : bsStaffBo.companyId != null) return false;
        if (companyName != null ? !companyName.equals(bsStaffBo.companyName) : bsStaffBo.companyName != null)
            return false;
        if (phone != null ? !phone.equals(bsStaffBo.phone) : bsStaffBo.phone != null) return false;
        if (leave != null ? !leave.equals(bsStaffBo.leave) : bsStaffBo.leave != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = staffId;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (userNumber != null ? userNumber.hashCode() : 0);
        result = 31 * result + (companyId != null ? companyId.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (leave != null ? leave.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BsStaffBo{" +
                "staffId=" + staffId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", userNumber='" + userNumber + '\'' +
                ", companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", phone='" + phone + '\'' +
                ", leave='" + leave + '\'' +
                '}';
    }
}
