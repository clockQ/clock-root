package org.clock.utils.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.clock.utils.dao.ICommonDAO;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author clock
 */
@Repository("commonDao")
public class CommonDAOImpl implements ICommonDAO{

	@PersistenceContext 
	private EntityManager em;

	@Override
	public void add(Object entity) throws Exception {
		em.persist(entity);
	}

	@Override
	public void remove(Object entity) throws Exception {
		em.remove(em.merge(entity));
	}

	@Override
	public <T> T save(T entity) throws Exception {
		return em.merge(entity);
	}

	/**
	 * 传入实体类.class返回实体类名字
	 * 
	 * @param entityClass	要获取名字的class
	 * @return	实体类名字的字符串形式
	 */
	protected  <T> String getEntityName(Class<T> entityClass){
		String entityName = entityClass.getSimpleName();
		Entity entity = entityClass.getAnnotation(Entity.class);
		if(entity.name() != null && !entity.name().equals("")){
			entityName = entity.name();
		}
		return entityName;
	}

	@Override
	public <T>long getCount(Class<T> entityClass) throws Exception{
		return (long)em.createQuery("SELECT COUNT(*) FROM "+ getEntityName(entityClass)).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<String> findByParam(Class<T> entityClass, String colName, String param) throws Exception {
		String jpaql = "SELECT DISTINCT t." + colName + " FROM "+ getEntityName(entityClass) + " t WHERE t." + colName + " LIKE :code ORDER BY " + colName;
		Query query = em.createQuery(jpaql);
		query.setParameter("code","%" + param +"%");
		return query.getResultList();
	}

	@Override
	public <T>T findById(Class<T> entityClass,Object id) throws Exception {
		return em.find(entityClass,id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> findAll(Class<T> entityClass) throws Exception {
		String jpaql = "SELECT t FROM "+ getEntityName(entityClass) + " t";
		Query query = em.createQuery(jpaql);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findAll(Class<T> entityClass, boolean needCache)
			throws Exception {
		String jpaql = "SELECT t FROM "+ getEntityName(entityClass) + " t";
		Query query = em.createQuery(jpaql);
		if(needCache==true)
			query.setHint("org.hibernate.cacheable", true);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findAll(Class<T> entityClass, int start, int pageSize,
			boolean needCache) {
		String jpaql = "SELECT t FROM "+ getEntityName(entityClass) + " t";
		Query query = em.createQuery(jpaql);
		if(needCache==true)
			query.setHint("org.hibernate.cacheable", true);
		query.setFirstResult((start-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	/**
	 * 生成data-jpa数据操作工厂
	 * 
	 * @param entityClass	要生成jpa数据操作工厂的实体类.class
	 * @return	数据操作工厂
	 */
	private <T, ID extends Serializable> SimpleJpaRepository<T, ID> getRepository(Class<T> entityClass) {
		SimpleJpaRepository<T, ID> repository = new SimpleJpaRepository<T, ID>(JpaEntityInformationSupport.getMetadata(entityClass, em), em);
		return repository;
	}

	@Override
	public <T, ID extends Serializable> void remove(Class<T> entityClass,ID id) throws Exception {
		this.getRepository(entityClass).delete(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findByJPAQL(String jpaql,Map<String,Object> param) throws Exception {
		Query query = em.createQuery(jpaql);
		if(param!=null){
			for(Object key : param.keySet()){
				query.setParameter(key.toString(), param.get(key.toString()));
			}
		}
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findByJPAQL(String jpaql, Map<String,Object> param, boolean needCache)
			throws Exception {
		Query query = em.createQuery(jpaql);
		if(needCache==true)
			query.setHint("org.hibernate.cacheable", true);
		if(param!=null){
			for(Object key : param.keySet()){
				query.setParameter(key.toString(), param.get(key.toString()));
			}
		}
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findBySQL(String sql, Map<String,Object> param, Class<T> entity) throws Exception {
		Query query = em.createNativeQuery(sql,entity);
		if(param!=null){
			for(Object key : param.keySet()){
				query.setParameter(key.toString(), param.get(key.toString()));
			}
		}
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findByJPAQL(String jpaql, Map<String, Object> param, int start, int pageSize) throws Exception {
		Query query = em.createQuery(jpaql);
		if(param!=null){
			for(Object key : param.keySet()){
				query.setParameter(key.toString(), param.get(key.toString()));
			}
		}
		query.setFirstResult((start-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findByJPAQL(String jpaql, Map<String, Object> param, int start, int pageSize, boolean needCache) throws Exception {
		Query query = em.createQuery(jpaql);
		if(needCache==true)
			query.setHint("org.hibernate.cacheable", true);
		if(param!=null){
			for(Object key : param.keySet()){
				query.setParameter(key.toString(), param.get(key.toString()));
			}
		}
		query.setFirstResult((start-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}
	
	/**
	 * 获得Criteria查询的实例,用于criteria 查询
	 * 
	 * @param entityClass 要操作的实体类.class
	 * @return	CriteriaQuery实例
	 */
	@SuppressWarnings("unused")
	private <T> CriteriaQuery<T> getCreateQuery(Class<T> entityClass){
		//获得Criteria查询工厂
		CriteriaBuilder cb = em.getCriteriaBuilder();
		//获得Criteria查询的实例
		return cb.createQuery(entityClass);
	} 
}
