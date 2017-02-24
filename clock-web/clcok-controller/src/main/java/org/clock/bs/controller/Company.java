package org.clock.bs.controller;

import org.clock.bs.api.IBsCompanySV;
import org.clock.bs.entity.BsCompany;
import org.clock.bs.restAPI.ICompany;
import org.springframework.beans.factory.annotation.Autowired;

public class Company implements ICompany{
	@Autowired
	private IBsCompanySV bsCompanySV;

	@Override
	public String login() {
		String result = null;
		try {
			BsCompany bsCompany = bsCompanySV.getCompanyById(1);
			result = bsCompany.toString();
			System.out.println(bsCompany);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}