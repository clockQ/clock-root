package org.clock.bs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the BS_COMPANY database table.
 * 
 */
@Entity
@Table(name="BS_COMPANY")
public class BsCompany implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BS_COMPANY_COMPANYID_GENERATOR", sequenceName="BS_STAFF$SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BS_COMPANY_COMPANYID_GENERATOR")
	@Column(name="COMPANY_ID")
	private long companyId;

	@Column(name="COMPANY_NAME")
	private String companyName;

	private String email;

	private String password;

	private BigDecimal phone;

    public BsCompany() {
    }

	public long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(long companyId) {
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

	public BigDecimal getPhone() {
		return this.phone;
	}

	public void setPhone(BigDecimal phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "BsCompany [companyId=" + companyId + ", companyName="
				+ companyName + ", email=" + email + ", password=" + password
				+ ", phone=" + phone + "]";
	}

}