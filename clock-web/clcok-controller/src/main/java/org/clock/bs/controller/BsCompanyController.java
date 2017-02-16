package org.clock.bs.controller;

import org.clock.bs.api.IBsCompanySV;
import org.clock.bs.entity.BsCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BsCompanyController {
	@Autowired
	private IBsCompanySV bsCompanySV;
	
	@RequestMapping("/getCompany")
	public String login() {  
		BsCompany bsCompany = null;
		try {
			bsCompany = bsCompanySV.getCompanyById(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bsCompany.toString();  
	}  
}