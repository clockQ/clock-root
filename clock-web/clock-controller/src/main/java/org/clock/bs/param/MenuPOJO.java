package org.clock.bs.param;

import java.util.List;

public class MenuPOJO {
    //目录pojo包含一级目录和二级目录(暂时不包含三级目录)
	private String parent;
	private List<Object> childs;
	
	public MenuPOJO() {
		super();
	}
	
	public MenuPOJO(String parent, List<Object> childs) {
		super();
		this.parent = parent;
		this.childs = childs;
	}
	
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public List<Object> getChilds() {
		return childs;
	}
	public void setChilds(List<Object> childs) {
		this.childs = childs;
	}
	
	
}