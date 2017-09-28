<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html"; charset="utf-8" >
	<title>大猿ATM(atm.com)-综合性ATM机，全国统一价</title>
	<link rel="stylesheet" href="/regist_css/reset.css" type="text/css">
	<link rel="stylesheet" href="/regist_css/style.css" type="text/css">
</head>
<body>
<div id="head">
	<div class="wrap">
		<div class="logo">
			<a href="#"><img width="50px" height="50px" src="/images/dayuan.jpg" alt=""></a>
		</div>
		<h3>欢迎注册</h3>
	</div>
</div>
<div class="register">
	<form id="registForm">
		<ul class="register_box">
		<li class="user_info"><span><b>*</b>账户名：</span><input type="text" id="userName" name="userName" class="user_name"></li>
		<li class="user_info"><span><b>*</b>设置密码：</span><input type="password" id="password" name="password" class="user_password"></li>
		<li class="user_info"><span><b>*</b>确认密码：</span><input type="password" id="copyPassword" name="copyPassword" class="user_password"></li>
		<li class="user_info"><span><b>*</b> 邮箱：</span><input type="text" id="email" name="email" class="user_name"></li>
		<li class="user_info"><span><b>*</b> 验证码：</span><input type="text" id="code" name="code" class="user_name">
		<img id="codeImg" alt="" src="/code/create.do" onclick="createImg();"></li>
		
		<li class="agree"><input type="checkbox" checked="checked" id="agreement"><label for="agreement">我已阅读并同意</label><a href="#">《用户注册协议》</a></li>
		<li class="submit"><input type="button" value="注册" class="submit_btn" onclick="regist();"></li>
		</ul>
	</form>
</div>
<div id="footer">
	<p>客服热线：400-675-1234</p>
	<p>Copyright © 2006 - 2017 备案编号：123456789123</p>
	<div class="credit_rating">
		<a href="#"><img src="/images/pj.jpg" alt="信用评价"></a>
		<a href="#"><img src="/images/pj.jpg" alt="信用评价"></a>
		<a href="#"><img src="/images/pj.jpg" alt="信用评价"></a>
		<a href="#"><img src="/images/pj.jpg" alt="信用评价"></a>
	</div>
</div>
<script type="text/javascript" src="/js/jquery-mini.js"></script>
<script type="text/javascript" src="/js/jquery-validate.min.js"></script>
<script type="text/javascript" src="/js/messages_zh.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
		$("#registForm").validate({
		    rules: {
		    	userName: {
			        required: true,
			        minlength: 3,
			        maxlength: 18
			      },
			      
		    	password: {
			        required: true,
			        minlength: 3,
			        maxlength: 18
			      },
			      copyPassword: {
			        required: true,
			        minlength: 3,
			        maxlength: 18
			      },
				email: "required",
		    	code: "required",
	
		    },
		    messages: {
		    	username: {
		    		required : "请输入账号名",
		    		minlength : "账号最小长度为3",
		    		maxlength : "账号最大长度为18"
		    	},
		    	password: {
		    		required : "请输入密码",
		    		minlength : "密码最小长度为3",
		    		maxlength : "密码最大长度为18"
		    	},
		    	copyPassword: {
			        required: "请确认密码",
			        minlength: "密码最小长度为3",
			        maxlength: "密码最大长度为18"
			      },		    
				email: "请输入邮箱",

		    	code: "请输入验证码"
	    
		    }
		});
	});
	
	function createImg() {
		var token = Date.parse(new Date())/1000;
		$("#codeImg").attr("src", "/code/create.do?" + token);
	} 
	
	function regist() {
		
		if (!$("#registForm").valid()) {
			return false;
		}
		
		$.ajax({
	        url:'/user/regist.do',
	        type:'POST', //GET
	        async:true,    //或false,是否异步
	        data:{
	        	userName:$('#userName').val(),
	        	password:$('#password').val(),
	        	copyPassword:$('#copyPassword').val(),
	        	email:$('#email').val(),
	        	code:$('#code').val()
	        },
	        timeout:5000,    //超时时间
	        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
	        beforeSend:function(xhr){
	            console.log(xhr)
	            console.log('发送前')
	        },
	        success:function(data,textStatus,jqXHR){
	            var obj = data;
	            alert(obj.success);
	            
	            if (!data.success) {
	            	alert(data.message);
	            	return;
	            }
	            alert("注册成功")
	            
	            window.location.href='/user/toLogin.do';
	            
	        },
	        error:function(xhr,textStatus){
	            console.log('错误')
	            console.log(xhr)
	            console.log(textStatus)
	        },
	        complete:function(){
	            console.log('结束')
	        }
	    });
	}

</script>
</body>
</html>