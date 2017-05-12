package org.clock.bs.api;

import org.clock.bs.entity.BsProjectBo;
import java.util.List;

/**
 * Created by clock on 2017/4/24.
 */
public interface IBsProjectSV {
    /**
     * 录入新项目
     *
     * @param entity	传入新项目的实体类
     * @return	成功录入返回true<br>
     * 			失败抛出异常
     */
    public BsProjectBo addProject(BsProjectBo entity) throws Exception;

    /**
     * 项目结项
     *
     * @param id 要关闭的项目id
     * @param message   关闭项目原因
     * @return  执行结果
     * @throws Exception    异常信息
     */
    public BsProjectBo closeProject(int id,String message) throws Exception;

    /**
     * 获取项目信息
     *
     * @param id	项目id
     * @return	返回项目实体类
     */
    public BsProjectBo getProjectById(int id) throws Exception;

    /**
     * 根据项目编号查询项目信息
     *
     * @param number	项目编号
     * @return	返回符合条件的项目结果集
     */
    public List<BsProjectBo> getCompanyByNumber(String number) throws Exception;

    /**
     * 因为某种原因,公司继续拨款给该项目
     *
     * @param id   本次拨款项目id
     * @param num   本次拨款金额金额
     * @return  拨款后的项目金额
     * @throws Exception
     */
    public BsProjectBo income(int id,int num) throws Exception;

    /**
     * 因为某人结算报销款,扣除项目金
     *
     * @param id   本次拨款项目id
     * @param num   本次扣除金额
     * @return  扣款后项目金额
     * @throws Exception
     */
    public BsProjectBo expend(int id,int num) throws Exception;

}
