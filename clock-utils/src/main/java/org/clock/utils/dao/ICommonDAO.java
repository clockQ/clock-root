package org.clock.utils.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author clcok
 */
public interface ICommonDAO {

	/**
	 * 添加对象
	 * 
	 * @param entity	新增实体对象
	 * @throws Exception
	 */
	public void add(Object entity) throws Exception;

	/**
	 * 在事物中删除对象,使用em.remove(em.merge(entity))
	 * 
	 * @param entity	要删除的实体对象,有主键即可
	 * @throws Exception
	 */
	public void remove(Object entity) throws Exception;

	/**
	 * 根据id删除对象
	 * 
	 * @param id	要删除的数据id
	 * @param entity	要删除的实体对象.class
	 * @throws Exception
	 */
	public <T, ID extends Serializable> void remove(Class<T> entityClass,ID id) throws Exception;

	/**
	 * 添加或更新实体,如果已经存在就更新,没有就保存,相当于(addOrUpdate)
	 * 
	 * @param entity	实体类引用
	 * @return	返回值为被保存后的对象(如果没有id会自动加入id值)
	 * @throws Exception
	 */
	public <T> T save(T entity) throws Exception;

	/**
	 * 返回数据表中的行数
	 * 
	 * @param entityClass	要查询的实体类.class
	 * @return
	 * @throws Exception
	 */
	public <T>long getCount(Class<T> entityClass) throws Exception;

	/**
	 * 按照输入模糊查询指定字段,并排序,去重<br>
	 * 可用于text标签输入时提示用户信息
	 * 
	 * @param entityClass	要查询的实体类
	 * @param colName	要查询的列
	 * @param param	要匹配的串儿
	 * @return	符合条件的列的list
	 * @throws Exception
	 */
	public <T> List<String> findByParam(Class<T> entityClass,String colName,String param) throws Exception;

	/**
	 * 通过主键查询出一行数据
	 * 
	 * @param entityClass	查询的实体类.class
	 * @param id	要查询的实体类id
	 * @return	返回查询结果
	 * @throws Exception
	 */
	public <T> T findById(Class<T> entityClass,Object id) throws Exception;

	/**
	 * 通过实体类型查询出全部数据
	 * 
	 * @param entityClass	查询的实体类.class
	 * @return	返回查询结果列表
	 * @throws Exception
	 */
	public <T> List<T> findAll(Class<T> entityClass) throws Exception;

	/**
	 * 通过实体类型查询出全部数据并可以缓存
	 * 
	 * @param entityClass	查询的实体类.class
	 * @param needCache	是否需要缓存 true => 缓存
	 * @return	返回查询结果列表
	 * @throws Exception
	 */
	public <T> List<T> findAll(Class<T> entityClass, boolean needCache) throws Exception;

	/**
	 * 通过实体类型查询出全部数据,分页返回并可以缓存
	 * 
	 * @param entityClass	查询的实体类.class
	 * @param start	开始页,从1开始
	 * @param pageSize	每页大小
	 * @param needCache	是否需要缓存 true => 缓存
	 * @return	结果list
	 */
	public <T> List<T> findAll(Class<T> entityClass, int start, int pageSize,boolean needCache) throws Exception ;


	/**
	 * 通过JPAQL查询语句进行查询
	 * 
	 * @param jpaql		jpa查询语句
	 * @param param		Map<String,Object>属性
	 * @return	查询返回的list信息
	 * @throws Exception
	 */
	public <T> List<T> findByJPAQL(String jpaql, Map<String,Object> param) throws Exception;

	/**
	 * 通过JPAQL查询语句进行查询并缓存
	 * 
	 * @param jpaql		jpa查询语句
	 * @param param		Map<String,Object>属性
	 * @param needCache	是否需要缓存 true => 缓存
	 * @return	查询返回的list信息
	 * @throws Exception
	 */
	public <T> List<T> findByJPAQL(String jpaql, Map<String,Object> param, boolean needCache)
			throws Exception;

	/**
	 * 通过SQL语句查询
	 * 
	 * @param sql	sql查询语句
	 * @param param	Map<String,Object>属性
	 * @param entity	要查询的实体类
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> findBySQL(String sql,Map<String,Object> param,Class<T> entity)throws Exception;

	/**
	 * 分页查询
	 * 
	 * @param jpaql	jpa查询语句
	 * @param param	Map<String,Object>属性
	 * @param start	开始页,从1开始
	 * @param pageSize	每页大小
	 * @return	结果list
	 */
	public <T> List<T> findByJPAQL(String jpaql, Map<String,Object> param, int start, int pageSize) throws Exception ;

	/**
	 * 分页查询,可以从缓存中获取
	 * 
	 * @param jpaql	jpa查询语句
	 * @param param	Map<String,Object>属性
	 * @param start	开始页,从1开始
	 * @param pageSize	每页大小
	 * @param needCache	是否需要缓存 true => 缓存
	 * @return	结果list
	 */
	public <T> List<T> findByJPAQL(String jpaql, Map<String,Object> param, int start, int pageSize,
			boolean needCache) throws Exception ;
}
