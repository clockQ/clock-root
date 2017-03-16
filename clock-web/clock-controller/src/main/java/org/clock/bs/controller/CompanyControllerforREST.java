package org.clock.bs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.clock.bs.api.IBsCompanySV;
import org.clock.bs.entity.BsCompany;
import org.clock.bs.ex.SVException;
import org.clock.bs.param.ResponsePOJO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;

@RestController
@RequestMapping("/company")
public class CompanyControllerforREST {
	transient final static private Log log = LogFactory.getLog(CompanyControllerforREST.class);
	@Reference(version = "1.0.0") 
	private IBsCompanySV bsCompanySV;

	/**
	 * 注册公司账号
	 * 
	 * @param entity 即将注册公司的实体引用
	 * @return	成功返回带id的公司引用
	 */
	@RequestMapping(value="/register", method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})  
	public ResponsePOJO register(@RequestBody @Valid BsCompany entity){
		ResponsePOJO responsePOJO = new ResponsePOJO();
		if(entity.getCompanyId()!=0){
			log.debug("系统出错,注册id不为空");
			responsePOJO.setResult(false);
			responsePOJO.setMessage("系统出错,注册id不为空");
			return responsePOJO;
		}
		if(entity.getCompanyName()==null||entity.getEmail()==null||entity.getPassword()==null){
			log.debug("注册信息不完整");
			responsePOJO.setResult(false);
			responsePOJO.setMessage("注册信息不完整");
			return responsePOJO;
		}
		try{
			BsCompany bsCompany = bsCompanySV.registeredCompany(entity);
			if(bsCompany.getCompanyId() != 0){
				log.debug("公司注册成功");
				responsePOJO.setResult(true);
				responsePOJO.setMessage("注册成功");
				responsePOJO.setData(bsCompany);
			}
		} catch (PersistenceException e) {
			log.error("可能该邮箱已被注册");
			responsePOJO.setResult(false);
			responsePOJO.setMessage("该邮箱已被注册");
		} catch (Exception e) {
			log.error(e.getMessage());
			responsePOJO.setResult(false);
			responsePOJO.setMessage("未知错误");
			e.printStackTrace();
		}
		return responsePOJO;
	}

	/**
	 * 登录公司账号
	 * 
	 * @param email	公司邮箱
	 * @param password 公司邮箱密码
	 * @return	登录成功返回公司信息
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})  
	public ResponsePOJO login(String email,String password) {
		ResponsePOJO responsePOJO = new ResponsePOJO();
		if(email==null||password==null||email.equals("")||password.equals("")){
			log.debug("用户名或者密码为空");
			responsePOJO.setResult(false);
			responsePOJO.setMessage("用户名或者密码为空");
			return responsePOJO;
		}
		try {
			BsCompany bsCompany = bsCompanySV.login(email);
			if(bsCompany != null){
				if(bsCompany.getPassword().equals(password)){
					log.debug("登录成功");
					responsePOJO.setResult(true);
					responsePOJO.setMessage("登录成功");
					responsePOJO.setData(bsCompany);
				}else{
					log.debug("密码错误");
					responsePOJO.setResult(false);
					responsePOJO.setMessage("密码错误");
				}
			}else {
				log.debug("公司邮箱未注册");
				responsePOJO.setResult(false);
				responsePOJO.setMessage("公司邮箱未注册");
			}
		} catch (SVException e) {
			log.debug(e.getMessage());
			responsePOJO.setResult(false);
			responsePOJO.setMessage(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			responsePOJO.setResult(false);
			responsePOJO.setMessage("未知错误");
			e.printStackTrace();
		}
		return responsePOJO;
	}

	/**
	 * 修改公司信息
	 * 
	 * @param id url的id
	 * @param entity 公司实体
	 * @return	成功返回true
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces={"application/json;charset=UTF-8"})  
	public ResponsePOJO modifyCompanyInfo(@PathVariable Integer id,@RequestBody @Valid BsCompany entity){
		ResponsePOJO responsePOJO = new ResponsePOJO();
		if(id == 0){
			log.debug("修改公司的id为0");
			responsePOJO.setResult(false);
			responsePOJO.setMessage("修改公司的id为0");
			return responsePOJO;
		}
		if(entity.getCompanyName()==null||entity.getEmail()==null||entity.getPassword()==null){
			log.debug("修改信息填写不完整");
			responsePOJO.setResult(false);
			responsePOJO.setMessage("修改信息填写不完整");
			return responsePOJO;
		}
		try {
			entity.setCompanyId(id);
			BsCompany bsCompany = bsCompanySV.modifyCompanyInfo(entity);
			log.debug("修改成功");
			responsePOJO.setResult(true);
			responsePOJO.setMessage("修改成功");
			responsePOJO.setData(bsCompany);
		}catch(Exception e){
			log.debug(e.getMessage());
			responsePOJO.setResult(false);
			responsePOJO.setMessage("未知错误");
			e.printStackTrace();
		}
		return responsePOJO;
	}

	/**
	 * 删除公司账号
	 * 
	 * @param id 公司id
	 * @return	成功返回true
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces={"application/json;charset=UTF-8"})  
	public ResponsePOJO removeCompany(@PathVariable Integer id){
		ResponsePOJO responsePOJO = new ResponsePOJO();
		try {
			bsCompanySV.removeCompany(id);
			log.debug("删除成功");
			responsePOJO.setResult(true);
			responsePOJO.setMessage("删除成功");
		} catch (SVException e) {
			log.debug(e.getMessage());
			responsePOJO.setResult(false);
			responsePOJO.setMessage(e.getMessage());
		} catch(Exception e){
			log.debug(e.getMessage());
			responsePOJO.setResult(false);
			responsePOJO.setMessage("未知错误");
			e.printStackTrace();
		}
		return responsePOJO;
	}

	/**
	 * 获得包含关键字的公司名称list
	 * 
	 * @param name	公司名称关键字
	 * @return
	 */
	@RequestMapping(value="/getCompanyByName", method=RequestMethod.GET, produces={"application/json;charset=UTF-8"})  
	public List<String> getCompanyByName(String name){
		List<String> result = new ArrayList<String>();
		try {
			result = bsCompanySV.getCompanyByName(name);
			log.debug("查询成功");
		} catch (Exception e) {
			log.debug("查询失败");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获得包含关键字的公司邮箱list
	 * 
	 * @param email	公司邮箱关键字
	 * @return	公司邮箱集合
	 */
	@RequestMapping(value="/getCompanyByEmail", method=RequestMethod.GET, produces={"application/json;charset=UTF-8"})  
	public List<String> getCompanyByEmail(String email){
		List<String> result = new ArrayList<String>();
		try {
			result = bsCompanySV.getCompanyByEmail(email);
			log.debug("查询成功");
		} catch (Exception e) {
			log.debug("查询失败");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据id获得公司信息
	 * 
	 * @param id 公司id
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces={"application/json;charset=UTF-8"})  
	public ResponsePOJO getCompanyById(@PathVariable Integer id){
		ResponsePOJO responsePOJO = new ResponsePOJO();
		try {
			BsCompany companyById = bsCompanySV.getCompanyById(id);
			if(companyById != null){
				log.debug("查询成功");
				responsePOJO.setResult(true);
				responsePOJO.setMessage("查询成功");
				responsePOJO.setData(companyById);
			}else if(companyById == null){
				log.debug("该公司不存在");
				responsePOJO.setResult(false);
				responsePOJO.setMessage("该公司不存在");
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
			responsePOJO.setResult(false);
			responsePOJO.setMessage("未知错误");
			e.printStackTrace();
		}
		return responsePOJO;
	}
}