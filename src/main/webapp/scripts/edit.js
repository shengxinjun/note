var paramter = "";
var userid = getCookie("userid");
$(document).ready(function() {
	// 为edit绑定单击事件
	$('#save_note').click(savenote);
	// 为add绑定单击事件
	$('#add_notebook').click(cleanNote);
	// 页面加载时查询所有笔记
	paramter = {'id':userid};
	refreshNoteTable();
	var username = getCookie("username");
	$('.profile-username').html(username);
});
//从cookie中取用户名
function getCookie(name) {
	var strcookie = document.cookie;// 获取cookie字符串
	var arrcookie = strcookie.split("; ");// 分割
	// 遍历匹配
	for (var i = 0; i < arrcookie.length; i++) {
		var arr = arrcookie[i].split("=");
		if (arr[0] == name) {
			return arr[1];
		}
	}
}//退出登录的时候清除cookie
function cleanCookie(){
	var myDate=new Date();    
    myDate.setTime(-1000);//设置时间    
    var data=document.cookie;    
    var dataArray=data.split("; ");    
    for(var i=0;i<dataArray.length;i++){    
         var varName=dataArray[i].split("=");    
         document.cookie=varName[0]+"=''; expires="+myDate.toGMTString();    
    }
    location.href = 'log_in.html';
}
// 点击新建时清空表单内容
function cleanNote() {
	myEditor.innerHTML = "";
	document.getElementById('input_note_title').value = "";
	document.getElementById('note_id').value = "";
	document.getElementById('note_share').value = "";
	document.getElementById('open_blog').text = "是否公开";
};
// 搜索功能
function search() {
	$(document).keyup(function(e) {// 捕获文档对象的按键弹起事件
		if (e.keyCode == 13) {// 按键信息对象以参数的形式传递进来了
			var keyword = document.getElementById('search_note').value;
			paramter = {
				'id':userid,
				'keyword' : keyword
			};
			refreshNoteTable();
			paramter = {'id':userid};
		}
	});
}
// 页面重定向方法
function a() {
	location.href = 'edit.html';
};
// 查询单条笔记功能
function editNote(id) {
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
			document.getElementById('input_note_title').value = result.name;
			document.getElementById('note_id').value = result.id;
			document.getElementById('note_share').value = result.share;
			if(result.share == 1){
				document.getElementById('open_blog').text = "公开";
			}else if (result.share ==2){
				document.getElementById('open_blog').text = "不公开";
			}
		}
	});
}
// 保存功能
function savenote() {
	var context = myEditor.innerHTML;
	var bz = myEditor.innerText;
	if(bz==''){
		alert('还是说点儿什么吧....');
		return;
	}
	var title = document.getElementById('input_note_title').value;
	if(title==''){
		alert('标题都不写，你跟我俩测试呢....');
		return;
	}
	var id = document.getElementById('note_id').value;
	var share = document.getElementById('note_share').value;
	if (id == null || id == "") {
		var paramter = {
			'title' : title,
			'context' : context,
			'owner' : userid,
			'bz' : bz,
			'share' :share
		};
		$.ajax({
			url : 'note/save.do',
			data : paramter,
			dataType : 'json',
			type : 'POST',
			success : function(result) {
				if (result == 1) {
					refreshNoteTable();
					cleanNote();
				}
			}
		});
	} else {
		var paramter = {
			'id' : id,
			'title' : title,
			'context' : context,
			'bz' : bz,
			'share':share
		};
		$.ajax({
			url : 'note/update.do',
			data : paramter,
			dataType : 'json',
			type : 'POST',
			success : function(result) {
				if (result == 1) {
					refreshNoteTable();
					cleanNote();
				}
			}
		});
	}
}

// 查询或者刷新note表格
function refreshNoteTable() {
	$.ajax({
				url : 'note/select.do',
				data : paramter,
				dataType : 'json',
				type : 'POST',
				success : function(obj) {
					var trStr = '';// 动态拼接table
					for (var i = 0; i < obj.length; i++) {// 循环遍历出json对象中的每一个数据并显示在对应的td中
						// trStr += '<tr>';
						trStr += '<button class="form-control fa fa-book" rel="tooltip-bottom" id = "'
								+ obj[i].id
								+ '" onclick="editNote(id)">'
								+ obj[i].name + '</button>';
						// trStr += '</tr><br>';
					}
					$("#table").html(trStr);
				},
				error : function(e) {

					top.location.href = 'log_in.html';

				}
			});
}