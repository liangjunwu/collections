<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/styles.css">
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/script/easyui/jquery.min.js"></script>
    <script type="text/javascript">
    	/* $(function(){
    		$("#submit").click(function(){
    			$.post(
        				"${pageContext.servletContext.contextPath}/user/login",
        				$("#main_form").serialize(),
        				function(_data){
        					if(_data.success){  //校验成功
        						window.location.href = "${pageContext.servletContext.contextPath}/user/toIndex";  //到主页面
        					}else{
        						$("#errorMsg").html(_data.obj);
        					}
        				},
        				"json"
        		);
    			return false;  //阻止form表单提交
    		});
    	}); */
    
    	function changeVerify(img){
    		img.src = img.src + "?i=" + Math.random();
    	}
    </script>
</head>
<body>
    <form id="main_form" method="post" action="${pageContext.servletContext.contextPath}/user/login">
        <div id="wrapper">
            <header class="header" style="background-color: #ADD8E6">
                <h1 class="header-title">用户登录</h1>
            </header>
            
            <div class="form-body">
                <div class="terms-block">
                	<span id="errorMsg" style="color:red;"></span>
                </div>
                <div class="field" id="username_field">
                    <label for="username">用户名</label>
                    <input type="text" id="username" name="username" value="">
                </div>

                <div class="field" id="password_field">
                    <label for="password">密码</label>
                    <input type="password" id="password" name="password">
                </div>
                
                <div class="field" id="verifyCode_field">
                	<label for="verfifyCode">验证码</label>
                	<input type="text" id="verfifyCode" name="verifyCode" style="width: 70%;">
                	<img onclick="changeVerify(this)" src="${pageContext.servletContext.contextPath}/user/verify" style="width: 25%; vertical-align: middle;"/>
                </div>

                <button style="background-color: #ADD8E6" type="submit" id="submit" class="btn-pay">登录
                </button>
                <p class="renews fine-print contact-support center-text">
                    All Right Reserved (2015-2017)&nbsp;&copy;
                </p>
            </div>
        </div>
    </form>
</body>
</html>
