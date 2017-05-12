package org.clock.bs.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by clock on 2017/5/3.
 */
@Entity
@Table(name = "bs_staff", schema = "clock_bs", catalog = "")
public class BsStaffBo implements Serializable {
    private int staffId;
    private String loginEmail;
    private String loginPassword;
    private String staffName;
    private String userNumber;
    private Integer companyId;
    private String companyName;
    private String phone;
    private Integer superior;
    private Integer staffLeave;

    @Id
    @Column(name = "STAFF_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    @Basic
    @Column(name = "LOGIN_EMAIL")
    public String getLoginEmail() {
        return loginEmail;
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
    }

    @Basic
    @Column(name = "LOGIN_PASSWORD")
    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    @Basic
    @Column(name = "STAFF_NAME")
    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
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
    @Column(name = "SUPERIOR")
    public Integer getSuperior() {
        return superior;
    }

    public void setSuperior(Integer superior) {
        this.superior = superior;
    }

    @Basic
    @Column(name = "STAFF_LEAVE")
    public Integer getStaffLeave() {
        return staffLeave;
    }

    public void setStaffLeave(Integer staffLeave) {
        this.staffLeave = staffLeave;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BsStaffBo bsStaffBo = (BsStaffBo) o;

        if (staffId != bsStaffBo.staffId) return false;
        if (loginEmail != null ? !loginEmail.equals(bsStaffBo.loginEmail) : bsStaffBo.loginEmail != null) return false;
        if (loginPassword != null ? !loginPassword.equals(bsStaffBo.loginPassword) : bsStaffBo.loginPassword != null)
            return false;
        if (staffName != null ? !staffName.equals(bsStaffBo.staffName) : bsStaffBo.staffName != null) return false;
        if (userNumber != null ? !userNumber.equals(bsStaffBo.userNumber) : bsStaffBo.userNumber != null) return false;
        if (companyId != null ? !companyId.equals(bsStaffBo.companyId) : bsStaffBo.companyId != null) return false;
        if (companyName != null ? !companyName.equals(bsStaffBo.companyName) : bsStaffBo.companyName != null)
            return false;
        if (phone != null ? !phone.equals(bsStaffBo.phone) : bsStaffBo.phone != null) return false;
        if (superior != null ? !superior.equals(bsStaffBo.superior) : bsStaffBo.superior != null) return false;
        if (staffLeave != null ? !staffLeave.equals(bsStaffBo.staffLeave) : bsStaffBo.staffLeave != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = staffId;
        result = 31 * result + (loginEmail != null ? loginEmail.hashCode() : 0);
        result = 31 * result + (loginPassword != null ? loginPassword.hashCode() : 0);
        result = 31 * result + (staffName != null ? staffName.hashCode() : 0);
        result = 31 * result + (userNumber != null ? userNumber.hashCode() : 0);
        result = 31 * result + (companyId != null ? companyId.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (superior != null ? superior.hashCode() : 0);
        result = 31 * result + (staffLeave != null ? staffLeave.hashCode() : 0);
        return result;
    }
}
