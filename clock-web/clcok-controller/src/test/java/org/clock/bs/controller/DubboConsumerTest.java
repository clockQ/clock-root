package org.clock.bs.controller;

import org.clock.bs.api.IBsCompanySV;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations={"file:src/test/resources/spring-dubbo-consumer.xml"})  
public class DubboConsumerTest {
	@Autowired
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
			System.out.println(bsCompanySV.getCompanyById(1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
