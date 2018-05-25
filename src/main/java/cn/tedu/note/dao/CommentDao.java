package cn.tedu.note.dao;

import java.util.Date;
import java.util.List;

import cn.tedu.note.entity.Comment;

public interface CommentDao {
	Integer AddComment(String id,String context,String note_id,String user_id,String bz,Date date);
	
	List<Comment> findCommentByBlogId(String id);
}




