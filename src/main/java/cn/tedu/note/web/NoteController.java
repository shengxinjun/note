package cn.tedu.note.web;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.note.entity.Note;
import cn.tedu.note.service.NoteService;
import cn.tedu.note.util.JsonResult;

@Controller
@RequestMapping("/note")
public class NoteController {
	
	@Resource
	private NoteService noteService;
	//注册
	@RequestMapping("/save.do")
	@ResponseBody
	public Integer save(String title,String context,
			String owner,String bz,Integer share,Date date){
		Integer result = noteService.save(title,context,owner,bz,share,date);
		return result;
	}
	//查询
	@RequestMapping("/select.do")
	@ResponseBody
	public List<Note> select(String id,String keyword){
		return noteService.select(id,keyword);
	}
	@RequestMapping("/find.do")
	@ResponseBody
	public Note findNoteById(String id){
		return noteService.findNoteById(id);
	}
	@RequestMapping("/update.do")
	@ResponseBody
	public Integer updateNoteById(String id,String title,String context,String owner,String bz,Integer share,Date date){
		return noteService.updateNoteById(id, title, context, owner, bz,share,date);
	}
	//查询所有公开的帖子并且按照更新时间排序
	@RequestMapping("/Note_Col.do")
	@ResponseBody
	public List<Note> Note_Col(){
		return noteService.Note_Col();
	}
}