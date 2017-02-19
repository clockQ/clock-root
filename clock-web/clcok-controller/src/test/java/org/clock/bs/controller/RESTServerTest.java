package org.clock.bs.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/resources/spring-dubbo-REST.xml"})  
public class RESTServerTest {
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test//通过
	public void testStartSTAF(){
		try {
			System.out.println("启动成功");
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
