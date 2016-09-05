<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String basepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="article" method="post" enctype="multipart/form-data">
        <input type="hidden" name="_method" value="PUT" /> 
        <input type="submit" value="PUT请求" />
    </form><br/>

</body>
</html>