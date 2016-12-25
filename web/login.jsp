<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>登录界面</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/demo.css">
	<link rel="stylesheet" type="text/css" href="css/background.css">
</head>
<body>
	
	<div class="demo" style="padding: 20px 0;">
		<div class="container">
			<div class="row">
				<div class="col-md-offset-3 col-md-6">
					<form class="form-horizontal" action="logIn.action">
						<span class="heading">用户登录</span>
						<div class="form-group">
							<input type="text" class="form-control" name="userName" placeholder="学工号">
							<i class="fa fa-user"></i>
						</div>
						<div class="form-group help">
							<input type="passWord" class="form-control" name="passWord" placeholder="密　码">
							<i class="fa fa-lock"></i>
							<a href="#" class="fa fa-question-circle"></a>
						</div>
						<div class="form-group">
							<div class="main-checkbox">
								<input type="checkbox" value="None" id="checkbox1" name="check"/>
								<label for="checkbox1"></label>
							</div>
							<span class="text">记住密码</span>
							<button type="submit" class="btn btn-default">登录</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div style="text-align:center;">
<p>中国科学技术大学软件学院工程实践平台</p>
</div>
</body>
</html>