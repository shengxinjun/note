package cn.tedu.note.web;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.note.entity.Comment;
import cn.tedu.note.entity.Note;
import cn.tedu.note.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {
	@Resource
	private CommentService commentService;
	
	@RequestMapping("/AddComment.do")
	@ResponseBody
	public Integer AddComment(String id,String context,String note_id,String user_id,String bz,Date date){
		return commentService.AddComment(id,context, note_id, user_id,bz,date);
	}
	//查询
	@RequestMapping("/findCommentByBlogId.do")
	@ResponseBody
	public List<Comment> findCommentByBlogId(String id){
		List<Comment> ss = commentService.findCommentByBlogId(id);
		return commentService.findCommentByBlogId(id);
	}
	
}
