package org.clock.bs.server;

import com.alibaba.dubbo.config.annotation.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.clock.bs.api.IBsProjectSV;
import org.clock.bs.entity.BsProjectBo;
import org.clock.utils.dao.ICommonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by clock on 2017/4/24.
 */
@Transactional
@Service(version = "1.0.0")
@Component
public class BsProjectSVImpl implements IBsProjectSV {
    transient final static private Log log = LogFactory.getLog(BsProjectSVImpl.class);

    @Autowired
    private ICommonDAO commonDao;

    @Override
    public BsProjectBo addProject(BsProjectBo entity) throws Exception {
        BsProjectBo bsProjectBo = commonDao.add(entity);
        log.debug("添加项目成功");
        return bsProjectBo;
    }

    @Override
    public BsProjectBo closeProject(int id, String message) throws Exception {
        BsProjectBo bsProjectBo = getProjectById(id);
        bsProjectBo.setAvailable((byte) 2);
        bsProjectBo.setCloseMessage(message);
        commonDao.save(bsProjectBo);
        log.debug("项目成功关闭");
        return bsProjectBo;
    }

    @Override
    public BsProjectBo getProjectById(int id) throws Exception {
        BsProjectBo bsProjectBo = commonDao.findById(BsProjectBo.class, id);
        log.debug("查询项目信息成功");
        return bsProjectBo;
    }

    @Override
    public List<BsProjectBo> getCompanyByNumber(String number) throws Exception {
        String jpaql = "select o from BsProjectBo o where o.number = :number";
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("number",number);
        List<BsProjectBo> results = commonDao.findByJPAQL(jpaql, param);
        log.debug("查询项目信息成功");
        return results;
    }

    @Override
    public BsProjectBo income(int id,int num) throws Exception {
        BsProjectBo bsProjectBo = getProjectById(id);
        int budget = bsProjectBo.getBudget();
        int balance = bsProjectBo.getBalance();
        budget += num;
        balance += num;
        bsProjectBo.setBudget(budget);
        bsProjectBo.setBalance(balance);
        commonDao.save(bsProjectBo);
        log.debug("项目成功注入新资金");
        return bsProjectBo;
    }

    @Override
    public BsProjectBo expend(int id,int num) throws Exception {
        BsProjectBo bsProjectBo = getProjectById(id);
        int balance = bsProjectBo.getBalance();
        balance -= num;
        bsProjectBo.setBalance(balance);
        commonDao.save(bsProjectBo);
        log.debug("项目报销已扣款");
        return bsProjectBo;
    }
}
