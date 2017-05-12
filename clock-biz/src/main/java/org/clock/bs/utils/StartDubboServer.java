package org.clock.bs.utils;

import org.I0Itec.zkclient.exception.ZkTimeoutException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartDubboServer {
	//启动Dubbo服务的入口函数
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {    
		//加载配置文件
		String[] locations = {"classpath:applicationContext.xml","classpath:spring-dubbo-provider.xml","classpath:spring-data.xml"};
		try{
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(locations);
			context.start();    
			System.out.println("服务接口加载成功");
			System.in.read(); // 为保证服务一直开着，利用输入流的阻塞来模拟   
		}catch(ZkTimeoutException ex){
			System.out.println("无法连接到注册中心");
			ex.printStackTrace();
		} 
	}    
}
