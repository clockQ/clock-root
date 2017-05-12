package org.clock.bs.server;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.clock.bs.entity.BsConsumeformBo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class BsStaffSVTest {
	transient final static private Log log = LogFactory.getLog(BsStaffSVTest.class);
	
	@Autowired
	private BsStaffSVImpl bsStaffSV;

	@Autowired
	private ProcessEngine processEngine;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testApprovalConsumeForm() {
		try {
//			bsStaffSV.approvalConsumeForm();
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.debug("=========================testGetCompanyByName测试通过");
	}

	@Test
	public void testSubmitConsumeForm() {
		try {
			BsConsumeformBo entity = new BsConsumeformBo();
			entity = bsStaffSV.submitConsumeForm(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.debug("=========================testGetCompanyByName测试通过");
	}

	@Test//测试activiti自动部署流程
	public void testActivityAutoDeploy() {
		try {
			RepositoryService repositoryService = processEngine.getRepositoryService();
			long count = repositoryService.createProcessDefinitionQuery().count();
			System.out.println("共部署了"+count+"流程");
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.debug("=========================testGetCompanyByName测试通过");
	}

	@Test
	public void testGetConsumeFormById() {
		BsConsumeformBo consumeFormById;
		try {
			consumeFormById = bsStaffSV.getConsumeFormById(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.debug("=========================testGetConsumeFormById通过");
	}

	@Test
	public void testGetConsumeFormByStaff() {
		List<BsConsumeformBo> consumeFormByStaff;
		try {
			consumeFormByStaff = bsStaffSV.getConsumeFormByStaff(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.debug("=========================testGetConsumeFormByStaf通过");
	}
}
