package cn.tedu.note.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.note.dao.CommentDao;
import cn.tedu.note.dao.UserDao;
import cn.tedu.note.entity.Comment;
import cn.tedu.note.entity.User;

@Service("commentService")
public class CommentServiceImpl
	implements CommentService{
	
	@Autowired
	private CommentDao commentDao;

	public Integer AddComment(String id,String context, String note_id, String user_id, String bz,Date date) {
		id = UUID.randomUUID().toString();
		date = new Date();
		return commentDao.AddComment(id,context, note_id, user_id, bz, date);
	}

	public List<Comment> findCommentByBlogId(String id) {
		// TODO Auto-generated method stub
		return commentDao.findCommentByBlogId(id);
	}
	
	
}





