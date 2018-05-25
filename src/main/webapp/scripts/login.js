/* 登录界面JS脚本程序 */

$(function(){
	keyLogin();
	//为登录按钮绑定单击事件
	$('#login').click(loginAction);
	$('#count').blur(checkName);
	$('#regist_username').blur(checkregName);
	
	$('#password').blur(checkPassword);
	$('#regist_button').click(saveAction);
});
//注册一个方法，回车实现登录
function keyLogin(){
	 if (event.keyCode==13)  //回车键的键值为13
		 loginAction(); //调用登录按钮的登录事件
	}
//登录按钮的动作
function loginAction(){
	console.log('login click!');
	//收集用户名和密码数据
	var name = $('#count').val();
	var password = $('#password').val();
	//验证用户名和密码
	var pass = checkName()+checkPassword();  
	if(pass!=2){
		return;
	}
	var paramter={'name':name, 
			'password':password};
	//发送Ajax请求
	$.ajax({
		url:'user/login.do',
		data:paramter,
		dataType:'json',
		type:'POST',
		success:function(result){
			//{state:0,data:, message}
			if(result.state==0){//SUCCESS
				console.log("SUCCESS");
				console.log(result.data);
				document.cookie=result.data.token+"=talkid";
				document.cookie="username="+result.data.name;
				document.cookie="userid="+result.data.id;
				location.href='edit.html';
				return;
			}else if(result.state==2){
				//用户名错误
				$('#count-msg').html(result.message);
				return;
			}else if(result.state==3){
				//密码错误
				$('#password-msg').html(result.message);
				return;
			}
			alert(result.message);
		},
		error:function(){
			alert('Ajax请求失败');
		}
	});
	
}

//注册按钮的动作
function saveAction(){
	var name = $('#regist_username').val();
	var nickname = $('#nickname').val();
	var password = $('#regist_password').val();
	var paramter={'name':name,'nickname':nickname,
			'password':password};
	var temp1 = checkregName();
	var temp2 = checkregPassword();
	var temp3 = checkfinal_password();
	var pass = checkregName()+checkregPassword()+checkfinal_password();
	if(pass!=3){
		return;
	}
	$.ajax({
		url:'user/save.do',
		data:paramter,
		dataType:'json',
		type:'POST',
		success:function(result){
			location.href='edit.html';
		},  
		error:function(){
			alert('Ajax请求失败');
		}
	});
}
//登录时检验用户名
function checkName(){
	var name = $('#count').val();
	if(name==null || name==""){
		//提示错误
		$('#count-msg').html('不能空');
		return false;
	}
	var reg = /^\w{3,10}$/;
	if(! reg.test(name)){
		$('#count-msg').html('长度不对');
		return false;
	}
	$('#count-msg').empty();
	return true;
} 
//注册时检验用户名
function checkregName(){
	var res ;
	var name = $('#regist_username').val();
	if(name==null || name==""){
		//提示错误
		$('#usename-msg').html('不能空');
		return false;
	}
	var reg = /^\w{3,10}$/;
	if(! reg.test(name)){
		$('#usename-msg').html('长度不对');
		return false;
	}else {
		var paramter = {'name':name};
		$.ajax({
			url:'user/checkUserName.do',
			data:paramter,
			dataType:'JSON',
			type:'POST',
			async: false,
			success:function(result){
				if(result == true){
					$('#usename-msg').html('用户名可用');
					res = 1;
				}
				else{
					$('#usename-msg').html('用户名不可用');
					res = 0;
				}
			}
		});
		if(res ==1){
			return true;
		}else
			return false;
	}
} 
//登录时检验密码
function checkPassword(){
	var password = $('#password').val();
	if(password==null || password==""){
		//提示错误
		$('#password-msg').html('不能空');
		return false;
	}
	var reg = /^\w{3,10}$/;
	if(! reg.test(password)){
		$('#password-msg').html('长度不对');
		return false;
	}
	$('#password-msg').empty();
	return true;
}
//注册时检验密码
function checkregPassword(){
	var password = $('#regist_password').val();
	if(password==null || password==""){
		//提示错误
		$('#password-msg').html('不能空');
		return false;
	}
	var reg = /^\w{3,10}$/;
	if(! reg.test(password)){
		$('#password-msg').html('长度不对');
		return false;
	}
	$('#password-msg').empty();
	return true;
}

function checkfinal_password(){
	var password = $('password').val();
	var finalpassword = $('inal_password').val();
	if(password==finalpassword){
		return true;
	}else return false;
}






