package org.clock.bs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * BsStaff entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BS_STAFF", schema = "BSSYS")
public class BsStaff implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7891177940116771725L;
	private Long staffId;
	private String username;
	private String password;
	private Long companyId;
	private String companyName;
	private String email;
	private Long phone;
	private Long leave;

	// Constructors

	/** default constructor */
	public BsStaff() {
	}

	/** minimal constructor */
	public BsStaff(Long leave) {
		this.leave = leave;
	}

	/** full constructor */
	public BsStaff(String username, String password, Long companyId,
			String companyName, String email, Long phone, Long leave) {
		this.username = username;
		this.password = password;
		this.companyId = companyId;
		this.companyName = companyName;
		this.email = email;
		this.phone = phone;
		this.leave = leave;
	}

	// Property accessors
	@SequenceGenerator(name = "generator")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "STAFF_ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getStaffId() {
		return this.staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	@Column(name = "USERNAME", length = 20)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "PASSWORD", length = 40)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "COMPANY_ID", precision = 12, scale = 0)
	public Long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	@Column(name = "COMPANY_NAME", length = 20)
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "EMAIL", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "PHONE", precision = 12, scale = 0)
	public Long getPhone() {
		return this.phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	@Column(name = "LEAVE", nullable = false, precision = 12, scale = 0)
	public Long getLeave() {
		return this.leave;
	}

	public void setLeave(Long leave) {
		this.leave = leave;
	}

	@Override
	public String toString() {
		return "BsStaff [staffId=" + staffId + ", username=" + username
				+ ", password=" + password + ", companyId=" + companyId
				+ ", companyName=" + companyName + ", email=" + email
				+ ", phone=" + phone + ", leave=" + leave + "]";
	}

}