package org.clock.bs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * BsCompany entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BS_COMPANY", schema = "BSSYS")
public class BsCompany implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4690393462402805944L;
	// Fields

	private Long companyId;
	private String companyName;
	private String password;
	private String email;
	private Long phone;

	// Constructors

	/** default constructor */
	public BsCompany() {
	}

	/** full constructor */
	public BsCompany(String companyName, String password, String email,
			Long phone) {
		this.companyName = companyName;
		this.password = password;
		this.email = email;
		this.phone = phone;
	}

	// Property accessors
	@SequenceGenerator(name = "generator")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "COMPANY_ID", unique = true, nullable = false, precision = 12, scale = 0)
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

	@Column(name = "PASSWORD", length = 40)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Override
	public String toString() {
		return "BsCompany [companyId=" + companyId + ", companyName="
				+ companyName + ", password=" + password + ", email=" + email
				+ ", phone=" + phone + "]";
	}

}