package cn.tedu.note.dao;

import cn.tedu.note.entity.User;

public interface UserDao {
	
	Integer saveUser(User user);
	
	User findUserById(String id);
	
	User findUserByName(String name);
	
}




