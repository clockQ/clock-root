package org.clock.bs.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * The persistent class for the bs_company database table.
 * 
 */
@Entity
@Table(name="bs_company")
@NamedQuery(name="BsCompany.findAll", query="SELECT b FROM BsCompany b")
public class BsCompany implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="COMPANY_ID")
	private int companyId;
	
	@Column(name="COMPANY_NAME")
	@NotEmpty
	private String companyName;

	@NotEmpty
	private String email;

	@NotEmpty
	private String password;

	private String phone;

	public BsCompany() {
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

	@Override
	public String toString() {
		return "BsCompany [companyId=" + companyId + ", companyName="
				+ companyName + ", email=" + email + ", password=" + password
				+ ", phone=" + phone + "]";
	}

}