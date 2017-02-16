package org.clock.utils.dao.impl;

import org.clock.utils.dao.ICommonDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import javax.persistence.Query;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.data.jpa.repository.support.JpaEntityInformation;
//import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
//import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

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
	public <T>T findById(Class<T> classType, long id) throws Exception {
		return em.find(classType, id);
	}


	//	
	//	@Override
	//	public void save(Object entity) throws Exception {
	//		
	//	}
	//
	//	@Override
	//	public void delete(Object entity) throws Exception {
	//		em.remove(entity);
	//	}
	//
	//	
	//
	//	@Override
	//	public List findByJPAQL(String jpaql,Map param) throws Exception {
	//		Query query = em.createQuery(jpaql);
	//		if(param!=null){
	//			for(Object key : param.keySet()){
	//				query.setParameter(key.toString(), param.get(key.toString()));
	//			}
	//		}
	//		return query.getResultList();
	//	}
	//
	//	@Override
	//	public List findByJPAQL(String jpaql, Map param, boolean needCache)
	//			throws Exception {
	//		Query query = em.createQuery(jpaql);
	//		if(needCache==true)
	//			query.setHint("org.hibernate.cacheable", true);
	//		if(param!=null){
	//			for(Object key : param.keySet()){
	//				query.setParameter(key.toString(), param.get(key.toString()));
	//			}
	//		}
	//		return query.getResultList();
	//	}
	//
	//	@Override
	//	public <T> T saveOrUpdate(T entity,Class<T> entityClass) throws Exception {
	//		JpaEntityInformation<T, ?> entityInfomation = JpaEntityInformationSupport.getMetadata(entityClass, em);
	//		if(entityInfomation.isNew(entity)) {
	//			em.persist(entity);
	//			return entity;
	//		} else {
	//			T t = em.merge(entity);
	//			return t;
	//		}
	//	}
	//	
	//	@Override
	//	public List findByJPAQL(String jpaql,Map param,int start,int pageSize) {
	//		Query query = em.createQuery(jpaql);
	//		if(param!=null){
	//			for(Object key : param.keySet()){
	//				query.setParameter(key.toString(), param.get(key.toString()));
	//			}
	//		}
	//		query.setFirstResult((start-1)*pageSize);
	//		query.setMaxResults(pageSize);
	//		return query.getResultList();
	//	}
	//	
	//	@Override
	//	public List findByJPAQL(String jpaql, Map param, int start, int pageSize,
	//			boolean needCache) {
	//		Query query = em.createQuery(jpaql);
	//		if(needCache==true)
	//			query.setHint("org.hibernate.cacheable", true);
	//		if(param!=null){
	//			for(Object key : param.keySet()){
	//				query.setParameter(key.toString(), param.get(key.toString()));
	//			}
	//		}
	//		query.setFirstResult((start-1)*pageSize);
	//		query.setMaxResults(pageSize);
	//		return query.getResultList();
	//	}
	//	
	//	@Override
	//	public <T, ID extends Serializable> void delete(ID id, Class<T> entityClass) {
	//		this.getRepository(entityClass).delete(id);
	//		
	//	}
	//	
	//	@Override
	//	public <T, ID extends Serializable> T findOne(ID id, Class<T> entityClass) {
	//		return this.getRepository(entityClass).findOne(id);
	//	}
	//	
	//	@Override
	//	public <T> List<T> findAll(Class<T> entityClass) {
	//		return this.getRepository(entityClass).findAll();
	//	}
	//	
	//	@Override
	//	public <T> Page<T> getPage(Pageable request, Class<T> entityClass) {
	//		return this.getRepository(entityClass).findAll(request);
	//	}
	//	
	//	@Override
	//	public <T> List<T> findAll(Specification<T> spec, Class<T> entityClass) {
	//		return this.getRepository(entityClass).findAll(spec);
	//	}
	//	
	//	@Override
	//	public <T> Page<T> getPageByFilter(Pageable request, Specification<T> spec, Class<T> entityClass) {
	//		return this.getRepository(entityClass).findAll(spec, request);
	//	}
	//	
	//	private <T, ID extends Serializable> SimpleJpaRepository<T, ID> getRepository(Class<T> entityClass) {
	//		SimpleJpaRepository<T, ID> repository = new SimpleJpaRepository<T, ID>(JpaEntityInformationSupport.getMetadata(entityClass, em), em);
	//		return repository;
	//	}
	//
	//	@Override
	//	public <T> List<T> saveOrUpdates(List<T> entitys, Class<T> entityClass) throws Exception{
	//		List<T> rtnList = new ArrayList<T>();
	//		for(T entity : entitys){
	//			T saveEntity = this.saveOrUpdate(entity, entityClass);
	//			rtnList.add(saveEntity);
	//		}
	//		return rtnList;
	//	}
	//	
	//	@Override
	//	public <T> List<T> findAllByCriteria(CriteriaQuery<T> cq){
	//		return em.createQuery(cq).getResultList();
	//	}
	//
	//	@Override
	//	public CriteriaBuilder getCriteriaBuilder() {
	//		return this.em.getCriteriaBuilder();
	//	}
	//
	//	@Override
	//	public List findBySql(String sql,Map param) throws Exception {
	//		Query query = em.createNativeQuery(sql);
	//		if(param!=null){
	//			for(Object key : param.keySet()){
	//				query.setParameter(key.toString(), param.get(key.toString()));
	//			}
	//		}
	//		return query.getResultList();
	//	}
	//
	//	@Override
	//	public <T> List<T> findBySql(String sql, Map param, Class<T> entity) throws Exception {
	//		Query query = em.createNativeQuery(sql,entity);
	//		if(param!=null){
	//			for(Object key : param.keySet()){
	//				query.setParameter(key.toString(), param.get(key.toString()));
	//			}
	//		}
	//		List<T> result = query.getResultList();
	//		return result;
	//	}
	//
	//	@Override
	//	public List findBySql(String sql, Map param, String resultMappingName) throws Exception {
	//		Query query = em.createNativeQuery(sql,resultMappingName);
	//		if(param!=null){
	//			for(Object key : param.keySet()){
	//				query.setParameter(key.toString(), param.get(key.toString()));
	//			}
	//		}
	//		List result = query.getResultList();
	//		return result;
	//	}
	//
	//	@Override
	//	public void deleteNewThread(Object entity) throws Exception {
	//		em.remove(em.merge(entity));
	//	}
	//
	//	

}
