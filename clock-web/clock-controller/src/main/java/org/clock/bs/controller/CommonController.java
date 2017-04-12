package org.clock.bs.controller;

import java.util.ArrayList;
import java.util.List;

import org.clock.bs.param.MenuPOJO;
import org.clock.bs.param.ResponsePOJOList;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/common")
public class CommonController {
//	transient final static private Log log = LogFactory.getLog(CommonController.class);
//	@Reference(version = "1.0.0") 
//	private IBsCompanySV bsCompanySV;

	/**
	 * 获取左侧菜单<br>
	 * 需要注意,返回的值必须是页面li标签的id,不然无法显示,请与前端确认好id的值
	 * 
	 * @param level	当前登录人员级别
	 * 
	 * @return	该人员可以操作的菜单的集合,
	 */
	@RequestMapping(value="/getMenu/{level}", method=RequestMethod.GET,produces={"application/json;charset=UTF-8"})  
	public ResponsePOJOList getMenu(@PathVariable Integer level){
		ResponsePOJOList responsePOJOList = new ResponsePOJOList();
		responsePOJOList.setResult(true);
		responsePOJOList.setMessage("菜单获得成功");
		List<Object> menus = new ArrayList<Object>();
		
		MenuPOJO form = new MenuPOJO();
		form.setParent("consumeManager");
		List<Object> formChilds = new ArrayList<Object>();
		formChilds.add("consumeForm");
		formChilds.add("consumeFlow");
		formChilds.add("consumeAgree");
		form.setChilds(formChilds);
		menus.add(form);
		
		MenuPOJO menu = new MenuPOJO();
		menu.setParent("index");
		List<Object> childs = new ArrayList<Object>();
		childs.add("indexParnet");
		childs.add("dashboard_2");
		childs.add("dashboard_3");
		childs.add("dashboard_4");
		childs.add("dashboard_5");
		menu.setChilds(childs);
		menus.add(menu);

		MenuPOJO menu2 = new MenuPOJO();
		menu2.setParent("layoutsParent");
		menus.add(menu2);
		
		responsePOJOList.setData(menus);
		return responsePOJOList;
	}
}
