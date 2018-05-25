package cn.tedu.note.service;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.tedu.note.dao.NoteDao;
import cn.tedu.note.entity.Note;
import cn.tedu.note.util.JsonResult;

@Service("noteService")
public class NoteServiceImpl
	implements NoteService{
	
	@Autowired
	private NoteDao	notedao;

	public Integer save(String title, String context, String owner, String bz,Integer share,Date date) {
		String id = UUID.randomUUID().toString();
		date = new Date();
		if(id!=null &&id !=""){
			return notedao.save(id,title,context,owner,bz,share,date);
		}
		return 0;
	}
	public Integer updateNoteById(String id,String title, String context, String owner, String bz,Integer share,Date date){
		date = new Date();
		return notedao.updateNoteById(id, title, context, owner, bz,share,date);
	}
	
	public List<Note> select(String id,String keyword) {
		if(keyword==null){
			keyword ="%%";
		}else{
			keyword ="%"+keyword+"%";
		}
		return notedao.select(id,keyword);
	}

	public Note findNoteById(String id) {
		// TODO Auto-generated method stub
		return notedao.findNoteById(id);
	}
	public List<Note> Note_Col() {
		return notedao.Note_Col();
	}
	
	
}





