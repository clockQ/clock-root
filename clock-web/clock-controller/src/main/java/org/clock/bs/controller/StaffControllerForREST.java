package org.clock.bs.controller;

import java.util.List;

import org.clock.bs.entity.BsStaffBo;
import org.clock.bs.ex.SVException;

import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.clock.bs.api.IBsStaffSV;
import org.clock.bs.entity.BsConsumeformBo;
import org.clock.bs.param.ResponsePOJO;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/staff")
@Component
public class StaffControllerForREST {
	transient final static private Log log = LogFactory.getLog(StaffControllerForREST.class);

	@Reference(version = "1.0.0") 
	private IBsStaffSV bsStaffSV;

	/**
	 * 登录员工账号
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
			BsStaffBo bsStaffBo = bsStaffSV.login(email);
			if(bsStaffBo != null){
				if(bsStaffBo.getLoginPassword().equals(password)){
					log.debug("登录成功");
					responsePOJO.setResult(true);
					responsePOJO.setMessage("登录成功");
					responsePOJO.setData(bsStaffBo);
				}else{
					log.debug("密码错误");
					responsePOJO.setResult(false);
					responsePOJO.setMessage("密码错误");
				}
			}else {
				log.debug("员工邮箱错误");
				responsePOJO.setResult(false);
				responsePOJO.setMessage("员工邮箱或密码错误");
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
	 * 提交报销单
	 * 
	 * @param entity 报销单的实体类
	 * @return	返回提交结果
	 */
	@RequestMapping(value="/submitConsumeForm", method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	public ResponsePOJO submitConsumeForm(@RequestBody BsConsumeformBo entity){
		ResponsePOJO responsePOJO = new ResponsePOJO();
		entity.setStaffId(1);
		if(entity.getStaffId() == null || entity.getStaffId().equals("")){
			log.debug("提交员工id为空");
			responsePOJO.setResult(false);
			responsePOJO.setMessage("提交员工id为空");
			return responsePOJO;
		}
		try{
			BsConsumeformBo bsConsumeformBo = bsStaffSV.submitConsumeForm(entity);
			if(!bsConsumeformBo.getConsumeFormNumber().equals("")){
				log.debug("报销单提交成功");
				responsePOJO.setResult(true);
				responsePOJO.setMessage("报销单提交成功");
				responsePOJO.setData(bsConsumeformBo);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			responsePOJO.setResult(false);
			responsePOJO.setMessage("未知错误");
			e.printStackTrace();
		}
		return responsePOJO;
	}

	/**
	 * 审批报销单
	 *
	 * @param entity 报销单的实体类
	 * @return	返回提交结果
	 */
	@RequestMapping(value="/approvalConsumeForm", method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	public ResponsePOJO approvalConsumeForm(BsConsumeformBo entity) throws Exception {
		return null;
	}

	/**
	 * 获取该员工全部报销单
	 *
	 * @param id 报销单的实体类
	 * @return	返回提交结果
	 */
	@RequestMapping(value="/submitConsumeForm3", method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	public BsConsumeformBo getConsumeFormById(int id) throws Exception {

		return null;
	}

	/**
	 * 获取指定id的报销单
	 *
	 * @param staffId 报销单的实体类
	 * @return	返回提交结果
	 */
	@RequestMapping(value="/submitConsumeForm4", method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	public List<BsConsumeformBo>  getConsumeFormByStaff(int staffId) throws Exception {

		return null;
	}
}