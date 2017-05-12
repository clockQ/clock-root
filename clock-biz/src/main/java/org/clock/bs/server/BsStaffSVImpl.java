package org.clock.bs.server;

import com.alibaba.dubbo.config.annotation.Service;
import org.activiti.engine.*;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.pvm.process.ProcessElementImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.clock.bs.api.IBsProjectSV;
import org.clock.bs.api.IBsStaffSV;
import org.clock.bs.entity.BsConsumeformBo;
import org.clock.bs.entity.BsProjectBo;
import org.clock.bs.entity.BsStaffBo;
import org.clock.bs.ex.SVException;
import org.clock.utils.dao.ICommonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 员工接口的实现类
 */
@Transactional
@Service(version = "1.0.0")
@Component
public class BsStaffSVImpl implements IBsStaffSV {
    transient final static private Log log = LogFactory.getLog(BsStaffSVImpl.class);

    @Autowired
    private ICommonDAO commonDao;

    @Autowired
    private IBsProjectSV bsProjectSV;

    @Autowired
    private IdentityService identityService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @Override
    public BsStaffBo login(String email) throws Exception {
        BsStaffBo bsStaffBo = null;
        String jpaql = "SELECT t FROM BsStaffBo t WHERE t.loginEmail = :loginEmail";
        Map<String, Object> param = new HashMap<String,Object>();
        param.put("loginEmail",email);
        List<BsStaffBo> bsStaffList = commonDao.findByJPAQL(jpaql, param);
        if(bsStaffList.size() == 0){
            log.debug("该员工邮箱未注册");
        }else if(bsStaffList.size() > 1){
            log.error("员工登录邮箱查询结果不唯一");
            throw new SVException("员工登录邮箱查询结果不唯一");
        }else if(bsStaffList.size() == 1){
            bsStaffBo = bsStaffList.get(0);
            log.debug("员工登录成功");
        }
        return bsStaffBo;
    }

    @Override
    public BsConsumeformBo submitConsumeForm(BsConsumeformBo entity) throws Exception {
        //获取当前流水号
        String SQL = "CALL pro_consumeFormId('BX')";
        List<String> li = commonDao.executeSQL(SQL,null);
        if(li.size() == 0){
            log.error("生成流水号失败");
            return null;
        }
        entity.setConsumeFormNumber(li.get(0));
        entity.setFlag(1);
        entity.setMessage("等待第一审批人审核通过");
        entity = commonDao.add(entity);
        identityService.setAuthenticatedUserId(String.valueOf(entity.getStaffId()));
        //启动流程
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("consumeFlow",entity.getStaffId().toString());
        log.debug("流程实例ID:"+processInstance.getId());//流程实例ID
        log.debug("流程定义ID:"+processInstance.getProcessDefinitionId());//流程定义ID

        //获取流程中的任务TASK组件
        Task task = taskService.createTaskQuery().singleResult();
        //完成第一个任务
        log.debug("提交报销单:"+task.getName());
        //任务ID
        String taskId = task.getId();
        //完成任务的同时，设置流程变量，使用流程变量用来指定完成任务后，下一个连线
        Map<String, Object> variables = new HashMap<String, Object>();
        //获得工程信息
        BsProjectBo project = bsProjectSV.getProjectById(entity.getProjectId());
        //设置下一审批人为项目负责人
        variables.put("staffId", project.getHeaderId());
        taskService.complete(taskId,variables);
        log.debug("完成提交报销单：任务ID："+taskId);

        return entity;
    }

    @Override
    public BsConsumeformBo approvalConsumeForm(BsConsumeformBo entity) throws Exception {
        return null;
    }

    /**
     * 获得指定员工的所有待办事项
     * @param staffId
     */
    public void getAllTask(int staffId){
        //获取流程中的任务TASK组件
        List<Task> tasks = taskService.createTaskQuery()//创建任务查询对象
                .taskAssignee(String.valueOf(staffId))//指定个人任务查询，指定办理人
                .orderByTaskCreateTime().asc()//使用创建时间的升序排列
                .list();//返回列表
    }


    public void approvalConsumeForm(String taskId,int staffId) throws Exception {
        //完成任务的同时，设置流程变量，使用流程变量用来指定完成任务后，下一个连线
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("staffId", 1);
        variables.put("checkResult","通过");
//        processEngine.getTaskService()//与正在执行的任务管理相关的Service
//                .complete(taskId,variables);
        System.out.println("完成任务：任务ID："+taskId);
        //获取流程中的任务TASK组件
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(String.valueOf(staffId)).list();
        String processInstanceId = tasks.get(0).getProcessInstanceId();

//        ProcessInstance pi = runtimeService()// 表示正在执行的流程实例和执行对象
//                .createProcessInstanceQuery()// 创建流程实例查询
//                .processInstanceId(processInstanceId)// 使用流程实例ID查询
//                .singleResult();
        //准备执行审批操作任务
//        task = taskService.createTaskQuery().singleResult();
//        System.out.println("第二个流程名称:"+task.getName());
//        taskId = task.getId();
//
//        taskService.complete(taskId,variables);
//        System.out.println("完成任务：任务ID："+taskId);
//
//        //准备执行第三个任务
//        task = taskService.createTaskQuery().singleResult();
//        System.out.println("第三个流程名称:"+task.getName());
//        taskId = task.getId();
//        //完成任务的同时，设置流程变量，使用流程变量用来指定完成任务后，下一个连线
//        variables = new HashMap<String, Object>();
//        variables.put("staffId", 1);
//        variables.put("checkResult","通过");
//        taskService.complete(taskId,variables);
//        System.out.println("完成任务：任务ID："+taskId);
//
//        //准备执行第四个任务
//        task = taskService.createTaskQuery().singleResult();
//        System.out.println("第四个流程名称:"+task.getName());
//        taskId = task.getId();
//        //完成任务的同时，设置流程变量，使用流程变量用来指定完成任务后，下一个连线
//        variables = new HashMap<String, Object>();
//        variables.put("staffId", 1);
//        variables.put("checkResult","通过");
//        taskService.complete(taskId,variables);
//        System.out.println("完成任务：任务ID："+taskId);

    }


    public BsConsumeformBo appropriation(int id) throws Exception {
        BsConsumeformBo bsConsumeformBo = commonDao.findById(BsConsumeformBo.class, id);
        log.debug("根据id查询报销单信息成功");
        return bsConsumeformBo;
    }

    @Override
    public BsConsumeformBo getConsumeFormById(int id) throws Exception {
        BsConsumeformBo bsConsumeformBo = commonDao.findById(BsConsumeformBo.class, id);
        log.debug("根据id查询报销单信息成功");
        return bsConsumeformBo;
    }

    @Override
    public List<BsConsumeformBo>  getConsumeFormByStaff(int staffId) throws Exception {
        String jpaql = "SELECT t FROM BsConsumeformBo t WHERE t.staffId = :staffId";
        Map<String, Object> param = new HashMap<String,Object>();
        param.put("staffId",staffId);
        List<BsConsumeformBo> bsConsumeformBo = commonDao.findByJPAQL(jpaql, param);
        log.debug("查询id为:"+staffId+"的员工所有报销成功");
        return bsConsumeformBo;
    }
}
