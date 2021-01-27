package com.cuterwrite.dbfinal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuterwrite.dbfinal.dao.UserDAO;
import com.cuterwrite.dbfinal.dao.UserReceiveInfoDAO;
import com.cuterwrite.dbfinal.entity.User;
import com.cuterwrite.dbfinal.entity.UserReceiveInfo;
import com.cuterwrite.dbfinal.service.ReceiverService;

/**  
 * @author Pang S.Z.
 * @create 2021-01-24 09:02:38 
 */
@Service
public class ReceiverServiceImpl implements ReceiverService{
	@Autowired
	UserReceiveInfoDAO dao;
	@Autowired
	UserDAO userDao;

	@Override
	public UserReceiveInfo getInfo(String username) {
		User user=userDao.findByUsername(username);
		Long id=user.getId().longValue();
		return dao.selectByUserId(id);
	}

	@Override
	public void insert(UserReceiveInfo info) {
		dao.insert(info);
	}

	@Override
	public void update(Long id, UserReceiveInfo info) {
		dao.updateByPrimaryKeySelective(info);
	}

	@Override
	public void delete(Long id) {
		dao.deleteByPrimaryKey(id);
	}

}
