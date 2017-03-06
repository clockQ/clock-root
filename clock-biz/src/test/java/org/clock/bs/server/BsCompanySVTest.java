package org.clock.bs.server;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.clock.bs.entity.BsCompany;
import org.clock.bs.ex.SVException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class) 
//由于本测试类位于src/test/java下，而app-context.xml处于src/main/java下，所以需要使用file来获取，
//否则使用@ContextConfiguration(locations={"classpath:WEB-INF/app-context.xml"})来获取
@ContextConfiguration(locations={"file:src/main/resources/applicationContext.xml","file:src/main/resources/spring-data.xml"})  
public class BsCompanySVTest {
	transient final static private Log log = LogFactory.getLog(BsCompanySVTest.class);
	
	@Autowired
	private BsCompanySVImpl bsCompanySV;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetCompanyByName(){
		try {
			List<String> companyNameList = bsCompanySV.getCompanyByName("test");
			for(String companyName : companyNameList){
				log.debug(companyName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.debug("=========================testGetCompanyByName测试通过");
	}
	
	@Test
	public void testGetCompanyById(){
		try {
			BsCompany companyById = bsCompanySV.getCompanyById(1111);
			if(companyById != null){
				log.debug("查询成功");
			}else if(companyById == null){
				log.debug("该公司不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.debug("=========================testGetCompanyById测试通过");
	}
	
	@Test
	public void testRemoveCompany(){
		try {
			bsCompanySV.removeCompany(25);
		} catch (SVException e) {
			log.debug(e.getMessage());
		} catch(Exception e){
			e.printStackTrace();
		}
		log.debug("=========================testRemoveCompany测试通过");
	}
	
	@Test
	public void testLogin(){
		String email = "123@asiainfo.com";
		String passwd = "123qwe";
		try {
			BsCompany bsCompany = bsCompanySV.login(email);
			if(bsCompany != null){
				if(bsCompany.getPassword().equals(passwd)){
					log.debug("登录成功");
				}else{
					log.debug("密码错误");
				}
			}else {
				log.debug("该公司邮箱未注册");
			}
		} catch (SVException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.debug("=========================testLogin测试通过");
	}
	
	@Test
	public void testRegisteredCompany() {
		BsCompany entity = new BsCompany();
		try {
//			entity.setCompanyId(100);//不需要传入id
			entity.setCompanyName("testname3");
			entity.setEmail("testmail3664");
			entity.setPassword("testspring3");
			entity.setPhone("0432-123333333");
			entity = bsCompanySV.registeredCompany(entity);
			log.debug("公司注册成功"+entity);
		} catch (PersistenceException e) {
			log.error("可能该邮箱已被注册");
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		log.debug("=========================testRegisteredCompany测试通过");
	}
}
