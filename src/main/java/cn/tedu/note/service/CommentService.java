package cn.tedu.note.service;

import java.util.Date;
import java.util.List;

import cn.tedu.note.entity.Comment;
import cn.tedu.note.entity.User;

public interface CommentService {
	Integer AddComment(String id,String context,String note_id,String user_id,String bz,Date date);
	
	List<Comment> findCommentByBlogId(String id);
}



