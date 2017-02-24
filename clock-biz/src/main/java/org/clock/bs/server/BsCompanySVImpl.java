package org.clock.bs.server;

import org.clock.bs.api.IBsCompanySV;
import org.clock.bs.entity.BsCompany;
import org.clock.utils.dao.ICommonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bsCompanySV")
public class BsCompanySVImpl implements	IBsCompanySV {
	@Autowired
	private ICommonDAO commonDao;

	@Override
	public long registeredCompany(BsCompany entity) throws Exception {
		// TODO 待实现
		return 0;
	}

	@Override
	public int removeCompany(BsCompany entity) throws Exception {
		// TODO 待实现
		return 0;
	}

	@Override
	public BsCompany getCompanyById(int id) throws Exception {
		BsCompany bsCompanyResult = null;
		bsCompanyResult = commonDao.findById(BsCompany.class,id);
		return bsCompanyResult;
	}
}
