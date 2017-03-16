package org.clock.bs.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.clock.bs.api.IBsCompanySV;
import org.clock.bs.entity.BsCompany;
import org.clock.bs.ex.SVException;
import org.clock.utils.dao.ICommonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import com.alibaba.dubbo.config.annotation.Service;

@Transactional
@Service(version = "1.0.0")
public class BsCompanySVImpl implements	IBsCompanySV {
	transient final static private Log log = LogFactory.getLog(BsCompanySVImpl.class);
	
	@Autowired
	private ICommonDAO commonDao;

	@Override
	public BsCompany registeredCompany(BsCompany entity) throws Exception{
		BsCompany bsCompany = commonDao.save(entity);
		log.debug("公司注册成功");
		return bsCompany;
	}

	@Override
	public BsCompany login(String email) throws Exception{
		BsCompany bsCompany = null;
		String jpaql = "SELECT t FROM BsCompany t WHERE t.email = :email";
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("email",email);
		List<BsCompany> bsCompanyList = commonDao.findByJPAQL(jpaql, param);
		if(bsCompanyList.size() == 0){
			bsCompany = null;
			log.debug("该公司邮箱未注册");
		}else if(bsCompanyList.size() > 1){
			log.error("公司登录邮箱查询结果不唯一");
			throw new SVException("公司登录邮箱查询结果不唯一");
		}else if(bsCompanyList.size() == 1){
			bsCompany = bsCompanyList.get(0);
			log.debug("公司登录成功");
		}
		return bsCompany;
	}

	@Override
	public BsCompany modifyCompanyInfo(BsCompany entity) throws Exception {
		BsCompany bsCompany = commonDao.save(entity);
		log.debug("公司信息修改成功");
		return bsCompany;
	}

	@Override//TODO 删除公司同时删除全部所属员工
	public boolean removeCompany(int id) throws Exception {
		try{
			commonDao.remove(BsCompany.class,id);
			log.debug("公司成功注销");
		}catch(EmptyResultDataAccessException e){
			log.debug("注销公司出现异常"+e.getMessage());
			throw new SVException("该公司已被注销");
		}
		return true;
	}

	@Override
	public BsCompany getCompanyById(int id) throws Exception {
		BsCompany bsCompany = commonDao.findById(BsCompany.class, id);
		log.debug("查询公司信息成功");
		return bsCompany;
	}

	@Override
	public List<String> getCompanyByName(String name) throws Exception {
		List<String> companyNameList = commonDao.findByParam(BsCompany.class,"companyName",name);
		log.debug("查询关键字带有\""+ name + "\"的公司成功,查询结果共:"+companyNameList.size()+"个");
		return companyNameList;
	}
	
	@Override
	public List<String> getCompanyByEmail(String email) throws Exception {
		List<String> companyEmailList = commonDao.findByParam(BsCompany.class,"email",email);
		log.debug("查询关键字带有\""+ email + "\"的公司邮箱成功,查询结果共:"+companyEmailList.size()+"个");
		return companyEmailList;
	}
}
