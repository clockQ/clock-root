package org.clock.bs.controller;

import org.clock.bs.param.ResponsePOJO;
import org.clock.bs.entity.BsCompanyBo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations={"classpath:spring-mvc.xml"})
public class CompanyControllerTest {
transient final static private Log log = LogFactory.getLog(CompanyControllerTest.class);
	@Autowired
	private CompanyControllerForREST company;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test//通过
	public void testLogin(){
		String email = "123@asiainfo.com";
		String passward = "123qwe";
		ResponsePOJO login = company.login(email,passward);
		log.debug("=========================testLogin测试通过"+login);
	}
	
	@Test//通过
	public void testRegister(){
		BsCompanyBo entity = new BsCompanyBo();
		entity.setCompanyName("杭州数码");
		entity.setEmail("shuma.com2");
		entity.setPassword("shuma2");
		entity.setPhone("0432-110");
		ResponsePOJO register = company.register(entity);
		log.debug("=========================testRegister测试通过"+register);
	}
}
