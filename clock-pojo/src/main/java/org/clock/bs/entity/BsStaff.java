package org.clock.bs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the BS_STAFF database table.
 * 
 */
@Entity
@Table(name="BS_STAFF")
public class BsStaff implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BS_STAFF_STAFFID_GENERATOR", sequenceName="BS_STAFF$SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BS_STAFF_STAFFID_GENERATOR")
	@Column(name="STAFF_ID")
	private long staffId;

	@Column(name="COMPANY_ID")
	private BigDecimal companyId;

	@Column(name="COMPANY_NAME")
	private String companyName;

	private String email;

	private BigDecimal leave;

	private String password;

	private BigDecimal phone;

	private String username;

    public BsStaff() {
    }

	public long getStaffId() {
		return this.staffId;
	}

	public void setStaffId(long staffId) {
		this.staffId = staffId;
	}

	public BigDecimal getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(BigDecimal companyId) {
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

	public BigDecimal getLeave() {
		return this.leave;
	}

	public void setLeave(BigDecimal leave) {
		this.leave = leave;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getPhone() {
		return this.phone;
	}

	public void setPhone(BigDecimal phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "BsStaff [staffId=" + staffId + ", companyId=" + companyId
				+ ", companyName=" + companyName + ", email=" + email
				+ ", leave=" + leave + ", password=" + password + ", phone="
				+ phone + ", username=" + username + "]";
	}

}