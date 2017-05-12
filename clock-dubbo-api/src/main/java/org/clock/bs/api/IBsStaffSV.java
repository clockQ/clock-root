package org.clock.bs.api;

import org.clock.bs.entity.BsConsumeformBo;
import org.clock.bs.entity.BsStaffBo;

import java.util.List;

/**
 * Created by clock on 2017/4/26.
 */
public interface IBsStaffSV {
    /**
     * 员工登录
     *
     * @param email	登录邮箱
     * @return	登录成功:返回登录y员工信息<br>
     * 			登录失败:返回null
     * @throws Exception 查询结果为多个值时抛出SVException
     */
    public BsStaffBo login(String email) throws Exception;

    /**
     * 提交报销单
     *
     * @param entity	刚创建的报销单
     * @return	保存成功:返回数据库中的报销单实体<br>
     * @throws Exception 抛出Exception
     */
    public BsConsumeformBo submitConsumeForm(BsConsumeformBo entity) throws Exception;

    /**
     * 审批报销单
     *
     * @param entity	要审批的报销单
     * @return	保存成功:返回刚审批的报销单实体<br>
     * @throws Exception 抛出Exception
     */
    public BsConsumeformBo approvalConsumeForm(BsConsumeformBo entity) throws Exception;

    /**
     * 获取报销单
     *
     * @param id	要获得的报销单id
     * @return	查询成功:返回数据库中的报销单实体<br>
     * @throws Exception 查询结果为多个值时抛出Exception
     */
    public BsConsumeformBo getConsumeFormById(int id) throws Exception;

    /**
     * 查询所有指定员工的报销单
     * @param staffId 员工id
     * @return
     * @throws Exception
     */
    public List<BsConsumeformBo> getConsumeFormByStaff(int staffId) throws Exception;
}
