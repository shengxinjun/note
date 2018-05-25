package cn.tedu.note.service;

import java.util.Date;
import java.util.List;

import cn.tedu.note.entity.Note;
import cn.tedu.note.util.JsonResult;

public interface NoteService {
	
	Integer save(String title,String context, String owner,String bz,Integer share,Date date);
	
	Integer updateNoteById(String id,String title, String context, String owner, String bz,Integer share,Date date);
	
	List<Note> select(String id,String keyword);
	
	Note findNoteById(String id);
	
	List<Note> Note_Col();
}



