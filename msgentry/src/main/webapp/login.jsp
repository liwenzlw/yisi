<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String basepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basepath%>">
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row" style="margin-top: 100px">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<form class="form-horizontal" action='login' method='post' role="form">
					<div class="form-group">
						<label for="username" class="col-sm-4 control-label">用户名：</label>
						<div class="col-sm-8">
							<input type="text"  required="required" class="form-control" name="username" id="username"
								placeholder="用户名">
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="col-sm-4 control-label">密码：</label>
						<div class="col-sm-8">
							<input type="password" required="required" class="form-control" id="password" name="password"
								placeholder="Password">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-8">
							<button type="submit" class="btn btn-default ">登录</button>
						</div>
					</div>
				</form>
			</div>
			<div class="col-md-4"></div>
		</div>
	</div>
</body>
</html>