package org.clock.bs.api;

import java.util.List;

import org.clock.bs.entity.BsCompany;

public interface IBsCompanySV {
	/**
	 * 新公司注册
	 * 
	 * @param entity	传入注册公司的实体类
	 * @return	成功注册返回true<br>
	 * 			失败抛出异常
	 */
	public BsCompany registeredCompany(BsCompany entity) throws Exception;
	
	/**
	 * 公司登录
	 * 
	 * @param email	登录邮箱
	 * @return	登录成功:返回登录公司信息<br>
	 * 			登录失败:公司未注册返回null
	 * @throws Exception 查询结果为多个值时抛出SVException
	 */
	public BsCompany login(String email) throws Exception;
	
	/**
	 * 修改公司信息
	 * 
	 * @param entity	要修改公司的实体引用
	 * @return	修改成功:返回新的公司信息<br>
	 * 			修改失败:抛出异常
	 */
	public BsCompany modifyCompanyInfo(BsCompany entity) throws Exception;
	
	/**
	 * 公司注销<BR>
	 * 
	 * @param id	要删除的公司id
	 * @return	成功删除返回true<br>
	 * 			失败抛出异常
	 */
	public boolean removeCompany(int id) throws Exception;
	
	/**
	 * 获取公司信息
	 * 
	 * @param id	公司id
	 * @return	返回公司实体类
	 */
	public BsCompany getCompanyById(int id) throws Exception;
	
	/**
	 * 获取公司名称集合
	 * 
	 * @param name	公司名称
	 * @return	返回符合条件的公司名称结果集
	 */
	public List<String> getCompanyByName(String name) throws Exception;
	
	/**
	 * 获取公司邮箱集合
	 * 
	 * @param email	公司邮箱
	 * @return	返回符合条件的公司邮箱结果集
	 */
	public List<String> getCompanyByEmail(String email) throws Exception;
}
