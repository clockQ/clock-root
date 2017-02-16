package org.clock.utils.dao;

//import java.io.Serializable;
//import java.util.List;
//import java.util.Map;

//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//
//import org.apache.poi.ss.formula.functions.T;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.domain.Specification;

/**
 * @author clcok
 */
public interface ICommonDAO {

	/**
	 * 添加对象
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public void add(Object entity) throws Exception;

	/**
	 * 通过主键查询
	 * 
	 * @param classType	保存的实体类.class
	 * @param id	要查询的实体类id
	 * @return
	 */
	public <T> T findById(Class<T> classType, long id) throws Exception;

	//	/**
	//	 * 保存或更新实体
	//	 * 
	//	 * @param entity	实体类引用
	//	 * @param entityClass	实体类.class
	//	 * @return	返回值为被保存后的对象(出现id字段)
	//	 * @throws Exception
	//	 */
	//	public <T> T saveOrUpdate(T entity, Class<T> entityClass) throws Exception;
	//
	//	/**
	//	 * 删除对象
	//	 * 
	//	 * @param entity
	//	 * @throws Exception
	//	 */
	//	public int remove(Object entity) throws Exception;
	//	
	//	/**
	//	 * 根据id删除实体
	//	 * 
	//	 * @param id
	//	 * @param entityClass
	//	 */
	//	public <T, ID extends Serializable> void delete(ID id, Class<T> entityClass);
	//
	//	/**
	//	 * 通过实体类查询全部
	//	 * 
	//	 * @param entityClass	实体类.class
	//	 * @return	查询出的全部行信息
	//	 */
	//	public <T> List<T> findAll(Class<T> entityClass);

	//	/**
	//	 * 通过查询语句进行查询
	//	 * 
	//	 * @param jpaql		jpa查询语句
	//	 * @param param		Map<String,Object>属性
	//	 * @return	查询返回的行信息
	//	 * @throws Exception
	//	 */
	//	@SuppressWarnings("rawtypes")
	//	public List findByJPAQL(String jpaql, Map param) throws Exception;
	//
	//	/**
	//	 * 通过查询语句进行查询
	//	 * 
	//	 * @param jpaql		jpa查询语句
	//	 * @param param		Map<String,Object>属性
	//	 * @param needCache 是否需要用缓存
	//	 * @return	查询返回的行信息
	//	 * @throws Exception
	//	 */
	//	@SuppressWarnings("rawtypes")
	//	public List findByJPAQL(String jpaql, Map param, boolean needCache)
	//			throws Exception;
	//
	//	/**
	//	 * 分页查询
	//	 * 
	//	 * @param jpaql	jpa查询语句
	//	 * @param param	Map<String,Object>属性
	//	 * @param start	开始页,从1开始
	//	 * @param pageSize	每页大小
	//	 * @return	结果页
	//	 */
	//	@SuppressWarnings("rawtypes")
	//	public List findByJPAQL(String jpaql, Map param, int start, int pageSize);
	//
	//	/**
	//	 * 分页查询,可以从缓存中获取
	//	 * 
	//	 * @param jpaql	jpa查询语句
	//	 * @param param	Map<String,Object>属性
	//	 * @param start	开始页,从1开始
	//	 * @param pageSize	每页大小
	//	 * @param needCache	是否需要从缓存取值
	//	 * @return
	//	 */
	//	@SuppressWarnings("rawtypes")
	//	public List findByJPAQL(String jpaql, Map param, int start, int pageSize,
	//			boolean needCache);
	//
	//	
	//	
	//	
	//
	//	
	//	/**
	//	 * 根据id查询一个实体
	//	 * @param id
	//	 * @param entityClass
	//	 * @return
	//	 */
	//	public <T, ID extends Serializable> T findOne(ID id, Class<T> entityClass);
	//
	//	/**
	//	 * 批量保存
	//	 * @param entitys
	//	 * @param entityClass
	//	 * @return
	//	 * @throws Exception 
	//	 */
	//	public <T> List<T> saveOrUpdates(List<T> entitys,Class<T> entityClass) throws Exception;
	//
	//	/**
	//	 * 通过cq对象进行查询
	//	 * @param cq
	//	 * @return
	//	 */
	//	public <T> List<T> findAllByCriteria(CriteriaQuery<T> cq);
	//
	//	/**
	//	 * 获取CriteriaBuilder
	//	 * @return
	//	 */
	//	public CriteriaBuilder getCriteriaBuilder();
	//
	//	/**
	//	 * 通过sql语句查询
	//	 * @param sql
	//	 * @param param
	//	 * @return
	//	 * @throws Exception
	//	 */
	//	public List findBySql(String sql,Map param)throws Exception;
	//
	//	/**
	//	 * 通过sql语句查询
	//	 * @param sql
	//	 * @param param
	//	 * @param entity
	//	 * @return
	//	 * @throws Exception
	//	 */
	//	public <T> List<T> findBySql(String sql,Map param,Class<T> entity)throws Exception;
	//
	//	/**
	//	 * 通过sql语句查询
	//	 * @param sql
	//	 * @param param
	//	 * @param resultMappingName
	//	 * @return
	//	 * @throws Exception
	//	 */
	//	public List findBySql(String sql,Map param,String resultMappingName)throws Exception;
	//
	//	/**
	//	 * 多线程操作数据库时,可能造成session关闭而引用还没销毁,造成无法delete,使用这个方法则会重新打开要删除的数据库连接,确保删除
	//	 * @param entity
	//	 * @throws Exception
	//	 */
	//	public void deleteNewThread(Object entity) throws Exception;

}
