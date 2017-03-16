package org.clock.bs.controller;

import org.clock.bs.api.IBsCompanySV;
import org.clock.bs.entity.BsCompany;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.dubbo.config.annotation.Reference;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations={"file:src/test/resources/spring-dubbo-consumer.xml"})  
public class DubboConsumerTest {
	@Reference(version = "1.0.0") 
	private IBsCompanySV bsCompanySV;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test//通过
	public void testDubboConsumer(){
		try {
			BsCompany bsCompany = bsCompanySV.login("123@asiainfo.com");
			System.out.println(bsCompany);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
