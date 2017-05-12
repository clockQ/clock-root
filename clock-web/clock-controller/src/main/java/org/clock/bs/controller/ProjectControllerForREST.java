package org.clock.bs.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.clock.bs.api.IBsProjectSV;
import org.clock.bs.entity.BsProjectBo;
import org.clock.bs.param.ResponsePOJO;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Created by clock on 2017/4/24.
 */
@RestController
@RequestMapping("/project")
@Component
public class ProjectControllerForREST {
    transient final static private Log log = LogFactory.getLog(ProjectControllerForREST.class);

    @Reference(version = "1.0.0")
    private IBsProjectSV bsProjectSV;

    /**
     * 根据项目编号查询项目
     *
     * @param number 项目编号
     * @return	项目实体类
     */
    @RequestMapping(value="/getProjectByNumber", method= RequestMethod.GET,produces={"application/json;charset=UTF-8"})
    public ResponsePOJO getProjectByNumber(String number){
        ResponsePOJO responsePOJO = new ResponsePOJO();
        number = number.trim();
        if(number.equals("")){
            log.debug("项目编号为空");
            responsePOJO.setResult(false);
            responsePOJO.setMessage("项目编号为空");
            return responsePOJO;
        }
        try{
            List<BsProjectBo> companyByNumber = bsProjectSV.getCompanyByNumber(number);
            if(companyByNumber.size() != 1){
                log.debug("查询结果有误");
                responsePOJO.setResult(false);
                responsePOJO.setMessage("查询结果有误");
                return responsePOJO;
            }
            log.debug("项目查询成功");
            responsePOJO.setResult(true);
            responsePOJO.setMessage("查询成功");
            responsePOJO.setData(companyByNumber.get(0));
        } catch (Exception e) {
            log.error(e.getMessage());
            responsePOJO.setResult(false);
            responsePOJO.setMessage("未知错误");
            e.printStackTrace();
        }
        return responsePOJO;
    }

}