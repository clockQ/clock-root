package org.clock.utils.dao;

import org.clock.bs.entity.BsCompany;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author clcok
 */
@RunWith(SpringJUnit4ClassRunner.class) 
// 由于本测试类位于src/test/java下，而app-context.xml处于src/main/java下，所以需要使用file来获取，
// 否则使用@ContextConfiguration(locations={"classpath:WEB-INF/app-context.xml"})来获取
@ContextConfiguration(locations={"file:src/main/java/applicationContext.xml","file:src/main/java/spring-data.xml"})  
public class CommonDaoTest {

	@Autowired
	private ICommonDAO commonDao;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdd() {
		//TODO 待测
		BsCompany entity = new BsCompany();
		entity.setCompanyId((long) 003);
		entity.setCompanyName("testname3");
		entity.setEmail("testmail3");
		entity.setPassword("testspring3");
		entity.setPhone((long) 123333333);
		try {
			commonDao.add(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test//通过
	public void testFindById() {
		try {
			BsCompany bsCompany = commonDao.findById(BsCompany.class,1);
			System.out.println(bsCompany.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
