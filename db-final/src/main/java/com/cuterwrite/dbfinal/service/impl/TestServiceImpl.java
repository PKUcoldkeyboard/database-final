package com.cuterwrite.dbfinal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuterwrite.dbfinal.dao.CategoryDAO;
import com.cuterwrite.dbfinal.dao.UserDAO;
import com.cuterwrite.dbfinal.entity.Category;
import com.cuterwrite.dbfinal.entity.User;
import com.cuterwrite.dbfinal.service.TestService;

/**  
 * @author Pang S.Z.
 * @create 2020-12-20 21:11:33 
 */
@Service
public class TestServiceImpl implements TestService{
	@Autowired
	UserDAO userDao;
	
	@Autowired
	CategoryDAO dao;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertData(User userToAdd) {
		userDao.insert(userToAdd);
		int i=1/0;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertUserInfo() {
		Category category=new Category();
		category.setName("test");
		dao.insert(category);
		int i=1/0;
	}

}
