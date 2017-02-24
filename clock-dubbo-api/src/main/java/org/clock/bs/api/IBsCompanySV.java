package org.clock.bs.api;

import org.clock.bs.entity.BsCompany;

public interface IBsCompanySV {
	/**
	 * 新公司注册
	 * @param entity	传入注册公司的实体类
	 * @return	成功注册返回数据库中的主键id<br>
	 * 			失败返回0
	 */
	public long registeredCompany(BsCompany entity) throws Exception;
	
	/**
	 * 公司注销
	 * @param entity	传入要销毁公司的实体类
	 * @return	成功删除返回1<br>
	 * 			失败返回0
	 */
	public int removeCompany(BsCompany entity) throws Exception;
	
	/**
	 * 获取公司信息
	 * @param id	公司id
	 * @return	返回公司实体类
	 */
	public BsCompany getCompanyById(int id) throws Exception;
}
