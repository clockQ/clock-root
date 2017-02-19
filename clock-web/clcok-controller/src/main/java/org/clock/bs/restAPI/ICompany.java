package org.clock.bs.restAPI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//根路径
@Path("company")
//返回类型 json优先
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
public interface ICompany {
	
	@GET
	@Path("login")
	public String login();
}
