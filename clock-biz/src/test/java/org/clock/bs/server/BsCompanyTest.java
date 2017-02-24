package org.clock.bs.server;

import org.clock.bs.api.IBsCompanySV;
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
public class BsCompanyTest {
	@Autowired
	private IBsCompanySV bsCompanySV;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test//TODO 待测
	public void testRegisteredCompany() {
//		BsCompany entity = new BsCompany();
//		entity.setCompanyId((long) 003);
//		entity.setCompanyName("testname3");
//		entity.setEmail("testmail3");
//		entity.setPassword("testspring3");
//		entity.setPhone(new BigDecimal("123333333"));
//		bsCompanySV.registeredCompany(entity);
	}
	
	@Test//通过
	public void testGetCompanyById(){
		try {
			System.out.println(bsCompanySV.getCompanyById(1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
