package org.clock.bs.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the bs_staff database table.
 * 
 */
@Entity
@Table(name="bs_staff")
@NamedQuery(name="BsStaff.findAll", query="SELECT b FROM BsStaff b")
public class BsStaff implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="STAFF_ID")
	private int staffId;

	@Column(name="COMPANY_ID")
	private int companyId;

	@Column(name="COMPANY_NAME")
	private String companyName;

	private String email;

	@Column(name="`LEAVE`")
	private String leave;

	private String name;

	private String password;

	private String phone;

	@Column(name="USER_NUMBER")
	private String userNumber;

	public BsStaff() {
	}

	public int getStaffId() {
		return this.staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public int getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLeave() {
		return this.leave;
	}

	public void setLeave(String leave) {
		this.leave = leave;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserNumber() {
		return this.userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	@Override
	public String toString() {
		return "BsStaff [staffId=" + staffId + ", companyId=" + companyId
				+ ", companyName=" + companyName + ", email=" + email
				+ ", leave=" + leave + ", name=" + name + ", password="
				+ password + ", phone=" + phone + ", userNumber=" + userNumber
				+ "]";
	}

}