package org.clock.utils.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@ContextConfiguration(locations={"file:src/test/resources/applicationContext.xml","file:src/test/resources/spring-data.xml"})  
public class CommonDaoTest {

	@Autowired
	private ICommonDAO commonDao;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test//通过
	public void testFindByParam(){
		try {
			List<String> results = commonDao.findByParam(BsCompany.class,"email","test");
			for(String bs : results){
				System.out.println(bs.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test//通过
	public void testFindBySQL(){
		String sql = "select T.* from BS_COMPANY T where T.EMAIL = :email";
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("email","testmail3");
		try {
			List<BsCompany> results = commonDao.findBySQL(sql,param,BsCompany.class);
			for(BsCompany bs : results){
				System.out.println(bs.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	@Test//通过
	public void testFindByJPAQL4(){
		String jpaql = "select o from BsCompany o where o.email = :email";
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("email","testmail3");
		try {
			List<BsCompany> results = commonDao.findByJPAQL(jpaql, param, 2,2,true);//显示第3,4个,并且从缓存中取
			for(BsCompany bs : results){
				System.out.println(bs.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	@Test//通过
	public void testFindByJPAQL3(){
		String jpaql = "select o from BsCompany o where o.email = :email";
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("email","testmail3");
		try {
			List<BsCompany> results = commonDao.findByJPAQL(jpaql, param, 2,2);//显示第3,4个
//			List<BsCompany> results = commonDao.findByJPAQL(jpaql, param, 2,2);//显示第1,2个
			for(BsCompany bs : results){
				System.out.println(bs.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	@Test//通过
	public void testFindByJPAQL2(){
		String jpaql = "select o from BsCompany o where o.email = :email";
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("email","testmail3");
		try {
			List<BsCompany> results = commonDao.findByJPAQL(jpaql, param, true);
			for(BsCompany bs : results){
				System.out.println(bs.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	@Test//通过
	public void testFindByJPAQL(){
		String jpaql = "select o from BsCompany o where o.companyId = :companyId";
//		String jpaql = "select o from BsCompany o where o.email = :email";
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("companyId",16);
//		param.put("email","testmail3");
		try {
			List<BsCompany> results = commonDao.findByJPAQL(jpaql, param);
			for(BsCompany bs : results){
				System.out.println(bs.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test//通过
	public void testFindAll(){
		try {
			List<BsCompany> findAll = commonDao.findAll(BsCompany.class);
			for(BsCompany bs : findAll){
				System.out.println(bs.toString());
			}
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
	
	@Test//通过
	public void testGetCount() throws Exception{
		System.out.println(commonDao.getCount(BsCompany.class));
	}
	
//	@Test//通过
	public void testSave() throws Exception{
		BsCompany entity = new BsCompany();
//		entity.setCompanyId(99999999);
		entity.setCompanyName("testname5");
		entity.setEmail("testmail5");
		entity.setPassword("testspring5");
		entity.setPhone("0432-125555555");
		try {
			commonDao.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test//通过
	public void testRemove(){
		BsCompany entity = new BsCompany();
		entity.setCompanyId(10);
		entity.setCompanyName("testname5");
		try {
			commonDao.remove(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test//通过
	public void testRemove2(){
		try {
			commonDao.remove(BsCompany.class,11);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	@Test//通过
	public void testAdd() {
		BsCompany entity = new BsCompany();
//		entity.setCompanyId(2);//主键自增
		entity.setCompanyName("testname3");
		entity.setEmail("testmail3");
		entity.setPassword("testspring3");
		entity.setPhone("0432-1233333");
		try {
			commonDao.add(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
