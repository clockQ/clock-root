package org.clock.bs.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.identity.User;
import org.clock.bs.entity.BsCompanyBo;
import org.clock.bs.api.IBsCompanySV;
import org.clock.bs.entity.BsStaffBo;
import org.clock.bs.ex.SVException;
import org.clock.utils.dao.ICommonDAO;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

@Transactional
@Service(version = "1.0.0")
@Component
class BsCompanySVImpl implements IBsCompanySV {
	transient final static private Log log = LogFactory.getLog(BsCompanySVImpl.class);
	
	@Autowired
	private ICommonDAO commonDao;

	@Autowired
	private IdentityService identityService;

	@Override
	public BsCompanyBo registeredCompany(BsCompanyBo entity) throws Exception{
		BsCompanyBo bsCompany = commonDao.add(entity);
		log.debug("公司注册成功");
		return bsCompany;
	}

	@Override
	public BsCompanyBo login(String email) throws Exception{
		BsCompanyBo BsCompanyBo = null;
		String jpaql = "SELECT t FROM BsCompanyBo t WHERE t.email = :email";
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("email",email);
		List<BsCompanyBo> BsCompanyBoList = commonDao.findByJPAQL(jpaql, param);
		if(BsCompanyBoList.size() == 0){
			BsCompanyBo = null;
			log.debug("该公司邮箱未注册");
		}else if(BsCompanyBoList.size() > 1){
			log.error("公司登录邮箱查询结果不唯一");
			throw new SVException("公司登录邮箱查询结果不唯一");
		}else if(BsCompanyBoList.size() == 1){
			BsCompanyBo = BsCompanyBoList.get(0);
			log.debug("公司登录成功");
		}
		return BsCompanyBo;
	}

	@Override
	public BsCompanyBo modifyCompanyInfo(BsCompanyBo entity) throws Exception {
		BsCompanyBo BsCompanyBo = commonDao.save(entity);
		log.debug("公司信息修改成功");
		return BsCompanyBo;
	}

	@Override
	public boolean removeCompany(int id) throws Exception {
		String jpaql = "DELETE FROM BsStaffBo t WHERE t.companyId = :companyId";
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("companyId",id);
		try{
			commonDao.remove(BsCompanyBo.class,id);
			commonDao.findByJPAQL(jpaql,param);
			log.debug("公司成功注销");
		}catch(EmptyResultDataAccessException e){
			log.debug("注销公司出现异常"+e.getMessage());
			throw new SVException("该公司已被注销");
		}
		return true;
	}

	@Override
	public BsCompanyBo getCompanyById(int id) throws Exception {
		BsCompanyBo BsCompanyBo = commonDao.findById(BsCompanyBo.class, id);
		log.debug("查询公司信息成功");
		return BsCompanyBo;
	}

	@Override
	public List<String> getCompanyByName(String name) throws Exception {
		List<String> companyNameList = commonDao.findByParam(BsCompanyBo.class,"companyName",name);
		log.debug("查询关键字带有\""+ name + "\"的公司成功,查询结果共:"+companyNameList.size()+"个");
		return companyNameList;
	}
	
	@Override
	public List<String> getCompanyByEmail(String email) throws Exception {
		List<String> companyEmailList = commonDao.findByParam(BsCompanyBo.class,"email",email);
		log.debug("查询关键字带有\""+ email + "\"的公司邮箱成功,查询结果共:"+companyEmailList.size()+"个");
		return companyEmailList;
	}

	@Override
	public BsStaffBo addStaff(BsStaffBo entity) throws Exception {
		BsStaffBo bsStaff = commonDao.add(entity);
		//在activiti的用户表中也添加一份
		User user = identityService.newUser(String.valueOf(bsStaff.getStaffId()));
		user.setFirstName(bsStaff.getStaffName());
		user.setEmail(bsStaff.getLoginEmail());
		user.setPassword(bsStaff.getLoginPassword());
		identityService.saveUser(user);
		//END
		log.debug("公司添加员工成功");
		return bsStaff;
	}

	@Override
	public BsStaffBo modifyStaffInfo(BsStaffBo entity) throws Exception {
		BsStaffBo bsStaff = commonDao.save(entity);
		identityService.deleteUser(String.valueOf(entity.getStaffId()));
		User user = identityService.newUser(String.valueOf(bsStaff.getStaffId()));
		user.setFirstName(bsStaff.getStaffName());
		user.setEmail(bsStaff.getLoginEmail());
		user.setPassword(bsStaff.getLoginPassword());
		identityService.saveUser(user);
		//END
		log.debug("员工信息修改成功");
		return bsStaff;
	}

	@Override
	public boolean removeStaff(int id) throws Exception {
		try{
			commonDao.remove(BsStaffBo.class,id);
			identityService.deleteUser(String.valueOf(id));
			log.debug("成功删除员工");
		}catch(EmptyResultDataAccessException e){
			log.debug("删除员工出现异常"+e.getMessage());
			throw new SVException("该员工已被删除");
		}
		return true;
	}
}
