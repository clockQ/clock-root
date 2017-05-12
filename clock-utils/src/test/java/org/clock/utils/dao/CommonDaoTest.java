package org.clock.utils.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.clock.bs.entity.BsCompanyBo;
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
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:spring-data.xml"})
public class CommonDaoTest {

	@Autowired
	private ICommonDAO commonDao;

	@Before
	public void setUp() throws Exception {
		System.out.println("Start Test CommonDaoTest");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test//通过
	public void testFindByParam(){
		try {
			List<String> results = commonDao.findByParam(BsCompanyBo.class,"email","test");
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
			List<BsCompanyBo> results = commonDao.findBySQL(sql,param,BsCompanyBo.class);
			for(BsCompanyBo bs : results){
				System.out.println(bs.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	@Test//通过
	public void testFindByJPAQL4(){
		String jpaql = "select o from BsCompanyBo o where o.email = :email";
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("email","testmail3");
		try {
			List<BsCompanyBo> results = commonDao.findByJPAQL(jpaql, param, 2,2,true);//显示第3,4个,并且从缓存中取
			for(BsCompanyBo bs : results){
				System.out.println(bs.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	@Test//通过
	public void testFindByJPAQL3(){
		String jpaql = "select o from BsCompanyBo o where o.email = :email";
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("email","testmail3");
		try {
			List<BsCompanyBo> results = commonDao.findByJPAQL(jpaql, param, 2,2);//显示第3,4个
//			List<BsCompanyBo> results = commonDao.findByJPAQL(jpaql, param, 2,2);//显示第1,2个
			for(BsCompanyBo bs : results){
				System.out.println(bs.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	@Test//通过
	public void testFindByJPAQL2(){
		String jpaql = "select o from BsCompanyBo o where o.email = :email";
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("email","testmail3");
		try {
			List<BsCompanyBo> results = commonDao.findByJPAQL(jpaql, param, true);
			for(BsCompanyBo bs : results){
				System.out.println(bs.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	@Test//通过
	public void testFindByJPAQL(){
		String jpaql = "select o from BsCompanyBo o where o.companyId = :companyId";
//		String jpaql = "select o from BsCompanyBo o where o.email = :email";
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("companyId",16);
//		param.put("email","testmail3");
		try {
			List<BsCompanyBo> results = commonDao.findByJPAQL(jpaql, param);
			for(BsCompanyBo bs : results){
				System.out.println(bs.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test//通过
	public void testFindAll(){
		try {
			List<BsCompanyBo> findAll = commonDao.findAll(BsCompanyBo.class);
			for(BsCompanyBo bs : findAll){
				System.out.println(bs.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test//通过
	public void testFindById() {
		try {
			BsCompanyBo BsCompanyBo = commonDao.findById(BsCompanyBo.class,1);
			System.out.println(BsCompanyBo.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test//通过
	public void testGetCount() throws Exception{
		System.out.println(commonDao.getCount(BsCompanyBo.class));
	}
	
	@Test//通过
	public void testSave() throws Exception{
		BsCompanyBo entity = new BsCompanyBo();
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
	
	@Test//通过
	public void testRemove(){
		BsCompanyBo entity = new BsCompanyBo();
		entity.setCompanyId(10);
		entity.setCompanyName("testname5");
		try {
			commonDao.remove(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test//通过
	public void testRemove2(){
		try {
			commonDao.remove(BsCompanyBo.class,11);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test//通过
	public void testAdd() {
		BsCompanyBo entity = new BsCompanyBo();
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
