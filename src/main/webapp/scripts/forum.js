$(document).ready(function() {
	Note_Col();
	$('#submit').click(AddComment);
});
function AddComment(){
	var comment = document.getElementById('comment').value;
	var note_id = document.getElementById('note_id').value;
	var user_id = getCookie("userid");
	if(comment==''){
		alert('还是说点儿什么吧....');
		return;
	}else if(note_id='' || user_id==''){
		alert('请刷新后重试....');
		return;
	}else{
		var paramter = {
				'context':comment,
				'note_id':note_id,
				'user_id':user_id
			};
		$.ajax({
			url : 'comment/AddComment.do',
			data : paramter,
			dataType : 'json',
			type : 'POST',
			success : function(result) {
				if(result==1)
				findCommentByBlogId(note_id);
				document.getElementById('comment').value = "";
			}
		});
	}
}
function findCommentByBlogId(id){
	var paramter = {
			'id':id
	};
	$.ajax({
		url : 'comment/findCommentByBlogId.do',
		data : paramter,
		dataType : 'JSON',
		type : 'POST',
		success : function(result)  {
			var trStr = '';// 动态拼接table
			for (var i = 0; i < result.length; i++) {// 循环遍历出json对象中的每一个数据并显示在对应的td中
				// trStr += '<tr>';
				trStr += '<div style = "border-top:1px solid #ccc;"><span class="btn btn-primary btn-sm glyphicon glyphicon-user">'
						+result[i].user_id+'</span><p>'+ result[i].context + '</p></div><br>';
				// trStr += '</tr><br>';
			}
			$("#Comments").html(trStr);
		},
		error : function(e){
			
		}
	});
}
//打开一条博客时，展示详情
function editBlog(id) {
	var paramter = {
		'id' : id
	};
	$.ajax({
		url : 'note/find.do',
		data : paramter,
		dataType : 'JSON',
		type : 'POST',
		success : function(result) {
			myEditor.innerHTML = result.context;
			document.getElementById('input_note_title').text = result.name;
			document.getElementById('note_id').value = result.id;
			findCommentByBlogId(result.id);
		}
	});
}
function Note_Col(){
	$.ajax({
		url : 'note/Note_Col.do',
		dataType : 'json',
		type : 'POST',
		success : function(obj) {
			var trStr = '';// 动态拼接table
			for (var i = 0; i < obj.length; i++) {// 循环遍历出json对象中的每一个数据并显示在对应的td中
				// trStr += '<tr>';
				trStr += '<div class="form-control fa fa-book" rel="tooltip-bottom" id = "'
						+ obj[i].id
						+ '" onclick="editBlog(id)">'
						+ obj[i].name + '</div>';
				// trStr += '</tr><br>';
			}
			$("#blog_cllection").html(trStr);
		},
		error : function(e) {

			top.location.href = 'log_in.html';

		}
	});
}